package brutepasta.front;

import brutepasta.entidades.Cliente;
import brutepasta.entidades.TipoProduto;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.TipoProdutoPersistencia;

public class AppTipoProduto {
    public AppTipoProduto() {
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
                    cadastrarTipoProduto();
                    break;
            }
        } while (opc != 5);
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