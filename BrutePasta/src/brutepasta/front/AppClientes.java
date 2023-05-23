package brutepasta.front;

import java.util.List;
import brutepasta.entidades.Cliente;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.persistencia.ClientePersistencia;

public class AppClientes {
	public AppClientes() {
		int opc;
		do {
			System.out.println("\n\n");
			System.out.println("*** CLIENTES ***");
			System.out.println("1 - Novo cliente");
			System.out.println("2 - Listar clientes");
			System.out.println("3 - Consultar cliente");
			System.out.println("4 - Alterar cliente");
			System.out.println("5 - Excluir cliente");
			System.out.println("6 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {
			case 1:
				incluirCliente();
				break;
			case 2:
				listarClientes();
				break;
			case 3:
				consultarCliente();
				break;
			case 4:
				alterarCliente();
				break;
			case 5:
				excluirCliente();
				break;
			}
		} while (opc != 6);
	}

	private static void incluirCliente() {
		System.out.println("\n\n*** INCLUSÃO DE CLIENTES ***");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
		if (ClienteNegocio.isCPF(objCliente.getCpf())) {
			if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o nome do cliente: "));
				ClientePersistencia.incluir(objCliente);
				System.out.println("\n\nInclusão bem sucedida...");
			} else {
				System.out.println("\n\nCliente já cadastrado...");
			}
		} else {
			System.out.println("\n\nCPF inválido...");
		}
	}

	private static void listarClientes() {
		System.out.println("\n\n*** LISTAGEM DE CLIENTES ***");
		Cliente objCliente = new Cliente();
		objCliente.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		for (Cliente item : ClientePersistencia.getClientes(objCliente)) {
			System.out.println("ID: " + item.getId());
			System.out.println("Nome: " + item.getNome());
			System.out.println("CPF: " + item.getCpf());
			System.out.println("------------------------------");
		}
	}

	private static void consultarCliente() {
		System.out.println("\n\n*** CONSULTA DE CLIENTES ***");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente que deseja consultar: "));
		if (ClienteNegocio.isCPF(objCliente.getCpf())) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				System.out.println("-----------------------");
				System.out.println("ID: " + objCliente.getId());
				System.out.println("Nome: " + objCliente.getNome());
				System.out.println("CPF: " + objCliente.getCpf());
				System.out.println("-----------------------");
			} else {
				System.out.println("\n\nCliente não cadastrado...");
			}
		} else {
			System.out.println("\n\nCPF inválido...");
		}
	}

	private static void alterarCliente() {
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente que deseja consultar: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if (objCliente != null) {
			System.out.println("-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer alterar o cliente? ");
			if (resp.equals("S")) {
				objCliente.setNome(Console.readString("\n\nInforme um novo nome para o cliente: "));
				if (ClientePersistencia.alterar(objCliente) == true) {
					System.out.println("\n\nAlteração realizada...");
				} else {
					System.out.println("\n\nA alteração não pôde ser realizada no momento...");
				}
			}
		} else {
			System.out.println("\n\nCliente não cadastrado...");
		}
	}

	private static void excluirCliente() {
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente que deseja consultar: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if (objCliente != null) {
			System.out.println("-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer excluir o cliente? ");
			if (resp.equals("S")) {
				if (ClientePersistencia.excluir(objCliente) == true) {
					System.out.println("\n\nO cliente foi excluído....");
				} else {
					System.out.println("\n\nNão foi possível excluir o cliente no momento...");
				}
			}
		} else {
			System.out.println("\n\nCliente não cadastrado...");
		}
	}
}
