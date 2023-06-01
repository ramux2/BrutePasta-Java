package brutepasta.front;

import brutepasta.entidades.Cliente;
import brutepasta.entidades.TipoProduto;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.TipoProdutoPersistencia;

public class AppFuncionario {
	public AppFuncionario() {
		int opc;
		do {
			System.out.println("\n\n====== FUNCIONÁRIO ======");
			System.out.println("1 - Produto");
			System.out.println("2 - Tipo produto");
			System.out.println("3 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {
				case 1:
					new AppProduto();
					break;
				case 2:
					new AppTipoProduto();
					break;
			}
		} while (opc != 7);
	}

	public static void cadastrarProduto() {
		System.out.println("\n\n====== CADASTRO DE PRODUTOS ======");
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

	public static void cadastrarTipoProduto() {
		System.out.println("\n\n====== CADASTRO TIPO DE PRODUTO ======");
		TipoProduto objTipoProduto = new TipoProduto();
		objTipoProduto.setNome(Console.readString("\n\nInforme o tipo do produto: "));
		if (TipoProdutoPersistencia.procurarPorNome(objTipoProduto) == null) {
			objTipoProduto.setNome(Console.readString("Informe o nome do cliente: "));
			TipoProdutoPersistencia.incluir(objTipoProduto);
			System.out.println("\n\nTipo de produto adicionado!!");
		} else {
			System.out.println("\n\nCliente já cadastrado.");
		}
	}
}
