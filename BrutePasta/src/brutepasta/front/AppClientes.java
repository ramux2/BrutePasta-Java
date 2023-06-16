package brutepasta.front;

import java.util.List;
import brutepasta.entidades.Cliente;
import brutepasta.entidades.Item;
import brutepasta.entidades.Pedido;
import brutepasta.entidades.Produto;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.negocio.ItemNegocio;
import brutepasta.persistencia.ClientePersistencia;

public class AppClientes {
	public AppClientes() {
		int opc;
		do {
			System.out.println("\n\n");
			System.out.println("====== CLIENTES ======");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Consultar cliente");
			System.out.println("3 - Alterar cliente");
			System.out.println("4 - Excluir cliente");
			System.out.println("5 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				consultarCliente();
				break;
			case 3:
				alterarCliente();
				break;
			case 4:
				excluirCliente();
				break;
			}
		} while (opc != 5);
	}

	private static void cadastrarCliente() {
		System.out.println("\n\n====== CADASTRO DE CLIENTES ======");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
		if (ClienteNegocio.isCPF(objCliente.getCpf())) {
			if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o nome do cliente: "));
				objCliente.setEndereco(Console.readString("Informe o endereço do cliente: "));
				objCliente.setNumeroTel(Console.readString("Informe o número de telefone: "));
				ClientePersistencia.incluir(objCliente);
				System.out.println("\n\nCadastro realizado!!");
			} else {
				System.out.println("\n\nCliente já cadastrado.");
			}
		} else {
			System.out.println("\n\nCPF inválido.");
		}
	}

	private static void consultarCliente() {
		System.out.println("\n\n====== CONSULTA DE CLIENTES ======\n");
		Cliente objCliente = new Cliente();
		Produto objProduto = new Produto();
		Pedido objPedido = new Pedido();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente que deseja consultar: "));
		if (ClienteNegocio.isCPF(objCliente.getCpf())) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				System.out.println("============================");
				System.out.println("ID: " + objCliente.getId());
				System.out.println("Nome: " + objCliente.getNome());
				System.out.println("CPF: " + objCliente.getCpf());
				System.out.println("Endereço: " + objCliente.getEndereco());
				System.out.println("Telefone: " + objCliente.getNumeroTel());
				System.out.println("============================");
				if (objCliente.getPedidos().isEmpty()) {
					System.out.println("\n\nCliente não possui pedidos ainda.");
				} else {
					System.out.println("\n\n====== PEDIDOS DO CLIENTE ======");
					float subTotalPedido = 0;
					for (Pedido pedido : objCliente.getPedidos()) {
						System.out.println("============================");
						System.out.println("Nome do cliente: " + pedido.getCliente().getNome());
						System.out.println("Número do pedido: " + pedido.getId());
						System.out.println("Data do pedido: " + pedido.getDataPedido());
						System.out.println("Endereço de entrega: " + pedido.getCliente().getEndereco());
						System.out.println("\n====== ITENS DO PEDIDO ======");
						System.out.println(pedido.getItens());
						for (Item item : pedido.getItens()) {
							System.out.println("Nome: " + item.getProduto().getNome());
							System.out.println("Preço: " + item.getProduto().getPreco());
							System.out.println("Quantidade: " + item.getQuantidade());
							subTotalPedido = ItemNegocio.calcularSubTotal(item, item.getProduto());
							System.out.println("Subtotal: R$" + subTotalPedido);
							System.out.println("============================");
						}
						System.out.println("Total do pedido: R$" + pedido.getValorTotal());
					}
				}
			} else {
				System.out.println("\n\nCliente não cadastrado.");
			}
		} else {
			System.out.println("\n\nCPF inválido.");
		}
	}

	private static void alterarCliente() {
		int opc;
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if (objCliente != null) {
			System.out.println("============================");
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("============================");
			String resp = Console.readString("\n\nAlterar dados do cliente [S]/[N]: ");
			if (resp.equals("S")) {
				do {
					System.out.println("\n\n");
					System.out.println("====== ALTERAÇÃO ======");
					System.out.println("1 - Alterar nome");
					System.out.println("2 - Alterar telefone");
					System.out.println("3 - Alterar endereço");
					System.out.println("4 - Sair");
					opc = Console.readInt("Opção: ");
					switch (opc) {
						case 1:
							objCliente.setNome(Console.readString("\n\nInforme um novo nome: "));
							if (ClientePersistencia.alterar(objCliente)) {
								System.out.println("\n\nAlteração realizada!!");
							} else {
								System.out.println("\n\nOcorreu um erro ao alterar dados do cliente.");
							}
							break;
						case 2:
							objCliente.setNumeroTel(Console.readString("\n\nInforme um novo número: "));
							if (ClientePersistencia.alterar(objCliente)) {
								System.out.println("\n\nAlteração realizada!!");
							} else {
								System.out.println("\n\nOcorreu um erro ao alterar dados do cliente.");
							}
							break;
						case 3:
							objCliente.setEndereco(Console.readString("\n\nInforme um novo endereço: "));
							if (ClientePersistencia.alterar(objCliente)) {
								System.out.println("\n\nAlteração realizada!!");
							} else {
								System.out.println("\n\nOcorreu um erro ao alterar dados do cliente.");
							}
							break;
					}
				}while (opc != 4);
			}
		} else {
			System.out.println("\n\nCliente não cadastrado.");
		}
	}

	private static void excluirCliente() {
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if (objCliente != null) {
			System.out.println("============================");
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("============================");
			String resp = Console.readString("\n\nExcluir cliente [S]/[N]: ");
			if (resp.equals("S")) {
				if (ClientePersistencia.excluir(objCliente)) {
					System.out.println("\n\nCliente excluído com sucesso!");
				} else {
					System.out.println("\n\nOcorreu um erro ao excluir o cliente.");
				}
			}
		} else {
			System.out.println("\n\nCliente não cadastrado.");
		}
	}
}
