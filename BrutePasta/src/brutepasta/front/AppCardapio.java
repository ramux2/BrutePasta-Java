package brutepasta.front;

import brutepasta.entidades.Produto;
import brutepasta.entidades.TipoProduto;
import brutepasta.persistencia.ProdutoPersistencia;
import brutepasta.persistencia.TipoProdutoPersistencia;

import java.util.List;

public class AppCardapio {
    public AppCardapio() {
        String opc;
        String continuar;
        Produto objProduto = new Produto();
        TipoProduto objTipoProduto = new TipoProduto();

        do {
            System.out.println("\n\n====== CARDÁPIO ======");
            List<TipoProduto> tipoProdutoList = TipoProdutoPersistencia.getTipoProduto();
            for (TipoProduto tipoProduto: tipoProdutoList) {
                System.out.println(tipoProduto.getId() + " - " + tipoProduto.getNome().toUpperCase());
            }
            opc = Console.readString("Informe o produto: ");
            TipoProduto tipoProdutoSelecionado = null;

            for (TipoProduto tipoProduto : tipoProdutoList) {
                if (tipoProduto.getNome().equalsIgnoreCase(opc)) {
                    tipoProdutoSelecionado = tipoProduto;
                    break;
                }
            }

            if (tipoProdutoSelecionado != null) {
                AppCardapio.exibirProdutos(tipoProdutoSelecionado);
            } else {
                System.out.println("Produto não encontrado!");
            }
            continuar = Console.readString("\n\nExibir cardápio novamente [S]/[N]: ");
        } while (continuar.equalsIgnoreCase("S"));
    }

    public static void exibirProdutos(TipoProduto tipoProduto){
        System.out.println("\n\n====== "+ tipoProduto.getNome().toUpperCase() +" ======");
        for (Produto produto: ProdutoPersistencia.getProdutos(tipoProduto)){
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Disponível: " + produto.getDisponibilidade());
            System.out.println("====================");
        }
    }
}
