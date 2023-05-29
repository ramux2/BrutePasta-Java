package brutepasta.front;

import brutepasta.entidades.Produto;
import brutepasta.persistencia.ProdutoPersistencia;

public class AppCardapio {
    public AppCardapio() {
        int opc;
        Produto objProduto = new Produto();

        do {
            System.out.println("\n\n====== CARDÁPIO ======");
            System.out.println("1 - Massas");
            System.out.println("2 - Molhos");
            System.out.println("3 - Carnes");
            System.out.println("4 - Risotos");
            System.out.println("5 - Saladas");
            System.out.println("6 - Acompanhamentos");
            System.out.println("7 - Bebidas");
            System.out.println("8 - Sair");
            opc = Console.readInt("Opção: ");

            switch (opc) {
                case 1:
                    listarMassas();
                    break;
                case 2:
                    listarMolhos();
                    break;
                case 3:
                    listarCarnes();
                    break;
                case 4:
                    listarRisotos();
                    break;
                case 5:
                    listarSaladas();
                    break;
                case 6:
                    listarAcomp();
                    break;
                case 7:
                    listarBebidas();
                    break;
            }
        } while (opc != 8);
    }

    public static void listarMassas(){
        String tipo = "massa";
        System.out.println("\n\n====== MASSAS ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }

    public static void listarMolhos(){
        String tipo = "molho";
        System.out.println("\n\n====== MOLHOS ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }

    public static void listarCarnes() {
        String tipo = "carne";
        System.out.println("\n\n====== CARNES ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }

    private static void listarRisotos() {
        String tipo = "risoto";
        System.out.println("\n\n====== RISOTOS ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }

    private static void listarSaladas() {
        String tipo = "salada";
        System.out.println("\n\n====== SALADAS ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }

    private static void listarAcomp() {
        String tipo = "acompanhamento";
        System.out.println("\n\n====== ACOMPANHAMENTOS ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }

    private static void listarBebidas() {
        String tipo = "bebida";
        System.out.println("\n\n====== BEBIDAS ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipo)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }
}
