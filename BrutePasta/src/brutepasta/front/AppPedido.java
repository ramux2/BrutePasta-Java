package brutepasta.front;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import brutepasta.entidades.*;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.negocio.PedidoNegocio;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.ProdutoPersistencia;
import brutepasta.persistencia.PedidoPersistencia;
import brutepasta.persistencia.EntregadorPersistencia;
import brutepasta.negocio.ItemNegocio;


public class AppPedido {
    public AppPedido() {
        int opc;

        do {
            System.out.println("\n\n");
            System.out.println("====== PEDIDOS ======");
            System.out.println("1 - Realizar pedido");
            System.out.println("2 - Excluir pedido");
            System.out.println("3 - Exibir cardápio");
            System.out.println("4 - Voltar");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                    realizarPedido();
                    break;
                case 2:
                    excluirPedido();
                    break;
                case 3:
                    new AppCardapio();
                    break;
            }
        } while (opc != 4);
    }

    public static void realizarPedido() {
        String resposta;
        Pedido objPedido = new Pedido();
        Cliente objCliente = new Cliente();
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        Date dataDate = null;
        String dataString;

        objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
        if (ClienteNegocio.isCPF(objCliente.getCpf())) {
            objCliente = ClientePersistencia.procurarPorCPF(objCliente);
            if (objCliente != null) {
                //Associando o objeto CLIENTE ao objeto PEDIDO
                objPedido.setCliente(objCliente);
                boolean dataValida = false;
                do {
                    dataString = Console.readString("Informe a data da venda: ");
                    try {
                        dataDate = (Date) formato.parse(dataString);
                        dataValida = true;
                        objPedido.setDataPedido(dataDate);
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Informe uma data válida.");
                    }
                } while (dataValida == false);

                do {
                    Produto objProduto = new Produto();
                    Item objItem = new Item();
                    objProduto.setNome(Console.readString("Informe o nome do produto: "));
                    objProduto = ProdutoPersistencia.procurarPorNome(objProduto);
                    if(objProduto != null) {
                        System.out.println("============================");
                        System.out.println("Nome: " + objProduto.getNome());
                        System.out.println("Descrição: " + objProduto.getDescricao());
                        System.out.println("Tipo do produto: " + objProduto.getTipoProduto().getNome());
                        System.out.println("Preço: " + objProduto.getPreco());
                        System.out.println("Disponibilidade: " + objProduto.getDisponibilidade());
                        System.out.println("============================");
                        if (objProduto.getDisponibilidade() > 0){
                            //Aqui está sendo associado o objeto PRODUTO ao objeto ITEM
                            objItem.setProduto(objProduto);
                            do {
                                objItem.setQuantidade(Console.readInt("Informe a quantidade: "));
                                System.out.println("SubTotal: " + ItemNegocio.calcularSubTotal(objItem, objProduto));
                                if ((objProduto.getDisponibilidade() - objItem.getQuantidade()) < 0) {
                                    System.out.println("Quantidade indisponível");
                                }
                            } while ((objProduto.getDisponibilidade() - objItem.getQuantidade()) < 0);
                            //Aqui está sendo adicionado o objeto ITEM ao objeto CARRINHO
                            objPedido.getItens().add(objItem);
                            objProduto.setDisponibilidade(objProduto.getDisponibilidade() - objItem.getQuantidade());
                        } else {
                            System.out.println("Produto indisponível no momento");
                        }
                    }
                    else {
                        System.out.println("\n\nProduto não cadastrado.");
                    }
                    resposta = Console.readString("Adicionar mais itens [S]/[N]: ");
                }while(resposta.equals("S"));

                List<Entregador> entregadoresDisponiveis = EntregadorPersistencia.getEntregadores();

                // Verificar se há entregadores disponíveis
                if (entregadoresDisponiveis.isEmpty()) {
                    System.out.println("Desculpe, não há entregadores disponíveis no momento.");
                    return;
                }

                // Gerar um índice aleatório para selecionar um entregador
                Random random = new Random();
                int indiceAleatorio = random.nextInt(entregadoresDisponiveis.size());

                // Obter o entregador selecionado
                Entregador entregadorSelecionado = entregadoresDisponiveis.get(indiceAleatorio);

                // Associar o entregador ao pedido
                objPedido.setEntregador(entregadorSelecionado);

                //Acrescenta a taxa de entrega ao entregador
                entregadorSelecionado.setTaxaEntrega(entregadorSelecionado.getTaxaEntrega() + 15);

                objPedido.setValorTotal(PedidoNegocio.calcularTotal(objPedido.getItens()));

                System.out.println("Valor total pedido: R$" + objPedido.getValorTotal());
                if(PedidoPersistencia.incluir(objPedido) == true) {
                    System.out.println("\n\nPedido realizada!");
                }
                else {
                    System.out.println("\n\nNão foi possível realizar a venda....");
                }
            }
        } else {
            System.out.println("CPF Inválido.");
        }
    }

    private static void excluirPedido() {
        Pedido objPedido = new Pedido();
        objPedido.setId(Console.readInt("\n\nInforme o número do pedido: "));
        objPedido = PedidoPersistencia.procurarPorId(objPedido);
        if (objPedido != null) {
            float subTotalPedido = 0;
            System.out.println("============================");
            System.out.println("Nome do cliente: " + objPedido.getCliente().getNome());
            System.out.println("Entregador: " + objPedido.getEntregador().getNome());
            System.out.println("Número do pedido: " + objPedido.getId());
            System.out.println("Data do pedido: " + objPedido.getDataPedido());
            System.out.println("Endereço de entrega: " + objPedido.getCliente().getEndereco());
            System.out.println("\n====== ITENS DO PEDIDO ======");
            for (Item itens : objPedido.getItens()) {
                System.out.println("Nome: " + itens.getProduto().getNome());
                System.out.println("Preço: " + itens.getProduto().getPreco());
                System.out.println("Quantidade: " + itens.getQuantidade());
                subTotalPedido = ItemNegocio.calcularSubTotal(itens, itens.getProduto());
                System.out.println("Subtotal: R$" + subTotalPedido);
                System.out.println("============================");
            }
            System.out.println("Total do pedido: R$" + objPedido.getValorTotal());
            String resp = Console.readString("\n\nExcluir pedido [S]/[N]: ");
            if (resp.equals("S")) {
                /*Cliente cliente = objPedido.getCliente();
                Entregador entregador = objPedido.getEntregador();
                if (entregador != null) {
                    entregador.getPedidos().remove(objPedido);
                    objPedido.setEntregador(null);
                }
                if (cliente != null) {
                    cliente.getPedidos().remove(objPedido);
                    objPedido.setCliente(null);
                }*/
                if (PedidoPersistencia.excluir(objPedido)) {
                    System.out.println("\n\nPedido excluído com sucesso!");
                } else {
                    System.out.println("\n\nOcorreu um erro ao excluir o pedido.");
                }
            }
        } else {
            System.out.println("\n\nPedido não cadastrado.");
        }
    }
}