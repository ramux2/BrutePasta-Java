package brutepasta.front;

import brutepasta.entidades.*;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.negocio.EntregadorNegocio;
import brutepasta.negocio.ItemNegocio;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.EntregadorPersistencia;

public class AppEntregador {
    public AppEntregador() {
        int opc;
        do {
            System.out.println("\n\n");
            System.out.println("====== ENTREGADOR ======");
            System.out.println("1 - Cadastrar entregador");
            System.out.println("2 - Consultar entregador");
            System.out.println("3 - Alterar entregador");
            System.out.println("4 - Excluir entregador");
            System.out.println("5 - Voltar");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                    cadastrarEntregador();
                    break;
                case 2:
                    consultarEntregador();
                    break;
                case 3:
                    alterarEntregador();
                    break;
                case 4:
                    excluirEntregador();
                    break;
            }
        } while (opc != 5);
    }

    private static void cadastrarEntregador() {
        System.out.println("\n\n====== CADASTRO DE ENTREGADOR ======");
        Entregador objEntregador = new Entregador();
        objEntregador.setPlacaVeiculo(Console.readString("Informe a placa do veículo Ex.: ABC1234: "));
        if (EntregadorNegocio.validaPlaca(objEntregador.getPlacaVeiculo())) {
            if (EntregadorPersistencia.procurarPorPlaca(objEntregador) == null) {
                objEntregador.setNome(Console.readString("Informe o nome do entregador: "));
                objEntregador.setTaxaEntrega(0);
                EntregadorPersistencia.incluir(objEntregador);
                System.out.println("\n\nCadastro realizado!!");
            } else {
                System.out.println("\n\nVeículo já cadastrado.");
            }
        } else {
            System.out.println("\n\nPlaca de veículo inválida.");
        }
    }

    private static void consultarEntregador() {
        System.out.println("\n\n====== CONSULTA DE ENTREGADOR ======");
        Entregador objEntregador = new Entregador();
        Produto objProduto = new Produto();
        Pedido objPedido = new Pedido();
        objEntregador.setPlacaVeiculo(Console.readString("Informe a placa para consultar o entregador: "));
        if (EntregadorNegocio.validaPlaca(objEntregador.getPlacaVeiculo())) {
            objEntregador = EntregadorPersistencia.procurarPorPlaca(objEntregador);
            if (objEntregador != null) {
                System.out.println("============================");
                System.out.println("ID: " + objEntregador.getId());
                System.out.println("Nome: " + objEntregador.getNome());
                System.out.println("Placa do veículo: " + objEntregador.getPlacaVeiculo());
                System.out.println("Total taxas de entrega: " + objEntregador.getTaxaEntrega());
                System.out.println("============================");
                if (objEntregador.getPedidos().isEmpty()) {
                    System.out.println("\n\nEntregador não possui pedidos ainda.");
                } else {
                    System.out.println("\n\n====== PEDIDOS ENTREGUES ======");
                    float subTotalPedido = 0;
                    for (Pedido pedido : objEntregador.getPedidos()) {
                        System.out.println("\n\nNome do cliente: " + pedido.getCliente().getNome());
                        System.out.println("Número do pedido: " + pedido.getId());
                        System.out.println("Data do pedido: " + pedido.getDataPedido());
                        System.out.println("Endereço de entrega: " + pedido.getCliente().getEndereco());
                        System.out.println("\n====== ITENS DO PEDIDO ======");
                        for (Item itens : pedido.getItens()) {
                            System.out.println("Nome: " + itens.getProduto().getNome());
                            System.out.println("Preço: " + itens.getProduto().getPreco());
                            System.out.println("Quantidade: " + itens.getQuantidade());
                            subTotalPedido = ItemNegocio.calcularSubTotal(itens, itens.getProduto());
                            System.out.println("Subtotal: R$" + subTotalPedido);
                            System.out.println("============================");
                        }
                        System.out.println("Total do pedido: R$" + pedido.getValorTotal());
                        System.out.println("Taxa de entrega: R$ 15,00");
                    }
                }
            } else {
                System.out.println("\n\nVeículo não cadastrado.");
            }
        } else {
            System.out.println("\n\nPlaca inválida.");
        }
    }

    private static void alterarEntregador() {
        int opc;
        Entregador objEntregador = new Entregador();
        objEntregador.setPlacaVeiculo(Console.readString("Informe a placa para alterar o entregador: "));
        objEntregador = EntregadorPersistencia.procurarPorPlaca(objEntregador);
        if (objEntregador != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objEntregador.getNome());
            System.out.println("Placa do Veículo: " + objEntregador.getPlacaVeiculo());
            System.out.println("============================");
            String resp = Console.readString("\n\nAlterar dados do entregador [S]/[N]: ");
            if (resp.equals("S")) {
                do {
                    System.out.println("\n\n");
                    System.out.println("====== ALTERAÇÃO ======");
                    System.out.println("1 - Alterar nome");
                    System.out.println("2 - Alterar placa do veículo");
                    System.out.println("3 - Sair");
                    opc = Console.readInt("Opção: ");
                    switch (opc) {
                        case 1:
                            objEntregador.setNome(Console.readString("\n\nInforme um novo nome: "));
                            if (EntregadorPersistencia.alterar(objEntregador)) {
                                System.out.println("\n\nAlteração realizada!!");
                            } else {
                                System.out.println("\n\nOcorreu um erro ao alterar dados do entregador.");
                            }
                            break;
                        case 2:
                            objEntregador.setPlacaVeiculo(Console.readString("Informe a nova placa do veículo"));
                            if (EntregadorNegocio.validaPlaca(objEntregador.getPlacaVeiculo())) {
                                if (EntregadorPersistencia.procurarPorPlaca(objEntregador) == null) {
                                    if (EntregadorPersistencia.alterar(objEntregador)) {
                                        System.out.println("\n\nAlteração realizada!!");
                                    } else {
                                        System.out.println("\n\nOcorreu um erro ao alterar dados do entregador.");
                                    }
                                } else {
                                    System.out.println("\n\nVeículo já cadastrado.");
                                }
                            }
                            break;
                    }
                }while (opc != 3);
            }
        } else {
            System.out.println("\n\nVeículo não cadastrado.");
        }
    }

    private static void excluirEntregador() {
        Entregador objEntregador = new Entregador();
        objEntregador.setPlacaVeiculo(Console.readString("\n\nInforme a placa do veículo do entregador: "));
        objEntregador = EntregadorPersistencia.procurarPorPlaca(objEntregador);
        if (objEntregador != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objEntregador.getNome());
            System.out.println("Placa do veículo: " + objEntregador.getPlacaVeiculo());
            System.out.println("============================");
            String resp = Console.readString("\n\nExcluir entregador [S]/[N]: ");
            if (resp.equals("S")) {
                if (EntregadorPersistencia.excluir(objEntregador)) {
                    System.out.println("\n\nEntregador excluído com sucesso!");
                } else {
                    System.out.println("\n\nOcorreu um erro ao excluir o entregador.");
                }
            }
        } else {
            System.out.println("\n\nVeículo não cadastrado.");
        }
    }
}
