package brutepasta.front;

import brutepasta.entidades.Cliente;
import brutepasta.entidades.Produto;
import brutepasta.entidades.TipoProduto;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.ProdutoPersistencia;
import brutepasta.persistencia.TipoProdutoPersistencia;

import java.util.List;


public class AppProduto {
    public AppProduto() {

        int opc;
        do {
            System.out.println("\n\n");
            System.out.println("====== FUNCIONÁRIO ======");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Alterar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("5 - Voltar");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    consultarProduto();
                    break;
                case 3:
                    alterarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
            }
        } while (opc != 5);
    }

    public static void cadastrarProduto() {
        System.out.println("\n\n====== CADASTRO DE PRODUTOS ======");
        Produto objProduto = new Produto();
        objProduto.setNome(Console.readString("\n\nInforme o nome do produto: "));
        if (ProdutoPersistencia.procurarPorNome(objProduto) == null) {
            TipoProduto objTipoProduto = new TipoProduto();
            objTipoProduto.setNome(Console.readString("Digite o tipo do produto: "));
            objTipoProduto = TipoProdutoPersistencia.procurarPorNome(objTipoProduto);
            if (objTipoProduto != null) {
                objProduto.setTipoProduto(objTipoProduto);
            } else {
                System.out.println("Tipo produto não cadastrado");
            }
            objProduto.setPreco(Console.readFloat("Informe o preço do produto: "));
            objProduto.setDescricao(Console.readString("Informe a descrição do produto: "));
            objProduto.setDisponibilidade(Console.readInt("Informe a disponibilidade do produto: "));
            ProdutoPersistencia.incluir(objProduto);
            System.out.println("\n\nCadastro realizado!!");
        } else {
            System.out.println("\n\nProduto já cadastrado.");
        }
    }

    private static void consultarProduto() {
        System.out.println("\n\n====== CONSULTA DE PRODUTO ======");
        Produto objProduto = new Produto();
        objProduto.setNome(Console.readString("Informe o nome do produto: "));
        objProduto = ProdutoPersistencia.procurarPorNome(objProduto);
        if (objProduto != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objProduto.getNome());
            System.out.println("Descrição: " + objProduto.getDescricao());
            System.out.println("Tipo do produto: " + objProduto.getTipoProduto().getNome());
            System.out.println("Preço: " + objProduto.getPreco());
            System.out.println("Disponibilidade: " + objProduto.getDisponibilidade());
            System.out.println("============================");
        }
    }



    private static void alterarProduto() {
        int opc;
        Produto objProduto = new Produto();
        objProduto.setNome(Console.readString("\n\nInforme o produto: "));
        objProduto = ProdutoPersistencia.procurarPorNome(objProduto);
        if (objProduto != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objProduto.getNome());
            System.out.println("Descrição: " + objProduto.getDescricao());
            System.out.println("Tipo do produto: " + objProduto.getTipoProduto().getNome());
            System.out.println("Preço: " + objProduto.getPreco());
            System.out.println("Disponibilidade: " + objProduto.getDisponibilidade());
            System.out.println("============================");
            String resp = Console.readString("\n\nAlterar dados do produto [S]/[N]: ");
            if (resp.equals("S")) {
                do {
                    System.out.println("\n\n");
                    System.out.println("====== ALTERAÇÃO ======");
                    System.out.println("1 - Alterar nome");
                    System.out.println("2 - Alterar preço");
                    System.out.println("3 - Alterar descrição");
                    System.out.println("4 - Alterar disponibilidade");
                    System.out.println("5 - Sair");
                    opc = Console.readInt("Opção: ");
                    switch (opc) {
                        case 1:
                            objProduto.setNome(Console.readString("\n\nInforme um novo nome: "));
                            if (ProdutoPersistencia.alterar(objProduto)) {
                                System.out.println("\n\nAlteração realizada!!");
                            } else {
                                System.out.println("\n\nOcorreu um erro ao alterar dados do produto.");
                            }
                            break;
                        case 2:
                            objProduto.setPreco(Console.readFloat("\n\nInforme um novo preço: "));
                            if (ProdutoPersistencia.alterar(objProduto)) {
                                System.out.println("\n\nAlteração realizada!!");
                            } else {
                                System.out.println("\n\nOcorreu um erro ao alterar dados do produto.");
                            }
                            break;
                        case 3:
                            objProduto.setDescricao(Console.readString("\n\nInforme uma nova descrição: "));
                            if (ProdutoPersistencia.alterar(objProduto)) {
                                System.out.println("\n\nAlteração realizada!!");
                            } else {
                                System.out.println("\n\nOcorreu um erro ao alterar dados do produto.");
                            }
                            break;
                        case 4:
                            objProduto.setDisponibilidade(Console.readInt("\n\nInforme uma nova disponibilidade: "));
                            if (ProdutoPersistencia.alterar(objProduto)) {
                                System.out.println("\n\nAlteração realizada!!");
                            } else {
                                System.out.println("\n\nOcorreu um erro ao alterar dados do produto.");
                            }
                            break;
                    }
                }while (opc != 5);
            }
        } else {
            System.out.println("\n\nProduto não cadastrado.");
        }
    }

    private static void excluirProduto() {
        Produto objProduto = new Produto();
        objProduto.setNome(Console.readString("\n\nInforme o nome do produto: "));
        objProduto = ProdutoPersistencia.procurarPorNome(objProduto);
        if (objProduto != null) {
            System.out.println("============================");
            System.out.println("Nome: " + objProduto.getNome());
            System.out.println("Tipo: " + objProduto.getTipoProduto().getNome());
            System.out.println("Preço: " + objProduto.getPreco());
            System.out.println("Descrição: " + objProduto.getDescricao());
            System.out.println("Disponibilidade: " + objProduto.getDisponibilidade());
            System.out.println("============================");
            String resp = Console.readString("\n\nExcluir produto [S]/[N]: ");
            if (resp.equals("S")) {
                if (ProdutoPersistencia.excluir(objProduto)) {
                    System.out.println("\n\nProduto excluído com sucesso!");
                } else {
                    System.out.println("\n\nOcorreu um erro ao excluir o produto.");
                }
            }
        } else {
            System.out.println("\n\nProduto não cadastrado.");
        }
    }
}
