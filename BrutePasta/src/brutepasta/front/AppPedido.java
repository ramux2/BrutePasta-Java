package brutepasta.front;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import brutepasta.entidades.*;
import brutepasta.negocio.ClienteNegocio;
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
            System.out.println("2 - Exibir cardápio");
            System.out.println("3 - Voltar");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                    realizarPedido();
                    break;
                case 2:
                    new AppCardapio();
            }
        } while (opc != 3);
    }

    public static void realizarPedido() {
        int opc;
        String resposta;
        Pedido objPedido = new Pedido();
        Cliente objCliente = new Cliente();
        Carrinho objCarrinho = new Carrinho();
        Produto objProduto = new Produto();
        Item objItem = new Item();
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        String dataString;
        Date dataFormatada = null;

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
                        dataFormatada = (Date) formato.parse(dataString);
                        dataValida = true;
                        objPedido.setDataPedido(dataFormatada);
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Informe uma data válida.");
                    }
                } while (dataValida == false);

                do {
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
                            objItem.setQuantidade(Console.readInt("Informe a quantidade: "));
                            System.out.println("SubTotal: " + ItemNegocio.calcularSubTotal(objItem, objProduto));
                            //Aqui está sendo adicionado o objeto ITEM ao objeto CARRINHO
                            objCarrinho.getItens().add(objItem);
                        }
                    }
                    else {
                        System.out.println("\n\nProduto não cadastrado.");
                    }
                    resposta = Console.readString("Adicionar mais itens [S]/[N]: ");
                }while(resposta.equals("S"));
                // Percorre os itens do carrinho
                for (Item item : objCarrinho.getItens()) {
                    Item novoItem = new Item();
                    novoItem.setProduto(item.getProduto());
                    novoItem.setQuantidade(item.getQuantidade());
                    // Adiciona o item à lista de produtos do pedido
                    objPedido.getItens().add(novoItem);
                }
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



                if(PedidoPersistencia.incluir(objPedido) == true) {
                    System.out.println("\n\nVenda realizada...");
                }
                else {
                    System.out.println("\n\nNão foi possível realizar a venda....");
                }
            }
        }
    }
}