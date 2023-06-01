package brutepasta.front;

import brutepasta.entidades.Cliente;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.persistencia.ClientePersistencia;

public class AppProduto {
    public AppProduto() {

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
                    cadastrarProduto();
                    break;

            }
        } while (opc != 5);
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
}