package brutepasta.front;

import java.util.List;

import brutepasta.entidades.*;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.negocio.ItemNegocio;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.TipoProdutoPersistencia;

public class AppTipoProduto {
    public AppTipoProduto() {
        int opc;
        do {
            System.out.println("\n\n");
            System.out.println("====== TIPO DE PRODUTO ======");
            System.out.println("1 - Cadastrar tipo de produto");
            System.out.println("2 - Consultar tipo de produto");
            System.out.println("3 - Alterar tipo de produto");
            System.out.println("4 - Excluir tipo de produto");
            System.out.println("5 - Voltar");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                    cadastrarTipoProduto();
                    break;
                case 2:
                    consultarTipoProduto();
                    break;
                case 3:
                    alterarTipoProduto();
                    break;
                case 4:
                    excluirTipoProduto();
                    break;
            }
        } while (opc != 5);
    }

    public static void cadastrarTipoProduto() {
        System.out.println("\n\n====== CADASTRO TIPO DE PRODUTO ======");
        TipoProduto objTipoProduto = new TipoProduto();
        objTipoProduto.setNome(Console.readString("\n\nInforme o tipo do produto: "));
        if (TipoProdutoPersistencia.procurarPorNome(objTipoProduto) == null) {
            TipoProdutoPersistencia.incluir(objTipoProduto);
            System.out.println("\n\nTipo de produto adicionado!!");
        } else {
            System.out.println("\n\nTipo de produto já cadastrado.");
        }
    }

    private static void consultarTipoProduto() {
        System.out.println("\n\n====== CONSULTA TIPO DE PRODUTO ======");
        List <TipoProduto> tipoProdutoList = TipoProdutoPersistencia.getTipoProduto();
        for (TipoProduto tipoProduto: tipoProdutoList) {
            System.out.println("Nome: " + tipoProduto.getNome().toUpperCase());
        }
    }

    private static void alterarTipoProduto() {
        int opc;
        TipoProduto objTipoProduto = new TipoProduto();
        objTipoProduto.setNome(Console.readString("\n\nInforme o tipo de produto: "));
        objTipoProduto = TipoProdutoPersistencia.procurarPorNome(objTipoProduto);
        if (objTipoProduto != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objTipoProduto.getNome());
            System.out.println("============================");
            String resp = Console.readString("\n\nAlterar dados do tipo de produto [S]/[N]: ");
            if (resp.equals("S")) {
                System.out.println("\n\n");
                System.out.println("====== ALTERAÇÃO ======");
                objTipoProduto.setNome(Console.readString("\n\nInforme um novo nome: "));
                if (TipoProdutoPersistencia.alterar(objTipoProduto)) {
                    System.out.println("\n\nAlteração realizada!!");
                } else {
                    System.out.println("\n\nOcorreu um erro ao alterar dados do tipo de produto.");
                }
            }
        } else {
            System.out.println("\n\nCliente não cadastrado.");
        }
    }

    private static void excluirTipoProduto() {
        TipoProduto objTipoProduto = new TipoProduto();
        objTipoProduto.setNome(Console.readString("\n\nInforme o tipo de Produto: "));
        objTipoProduto = TipoProdutoPersistencia.procurarPorNome(objTipoProduto);
        if (objTipoProduto != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objTipoProduto.getNome());
            System.out.println("============================");
            String resp = Console.readString("\n\nExcluir tipo de produto [S]/[N]: ");
            if (resp.equals("S")) {
                if (TipoProdutoPersistencia.excluir(objTipoProduto)) {
                    System.out.println("\n\nTipo de produto excluído com sucesso!");
                } else {
                    System.out.println("\n\nOcorreu um erro ao excluir o tipo de produto.");
                }
            }
        } else {
            System.out.println("\n\nTipo de produto não cadastrado.");
        }
    }
}