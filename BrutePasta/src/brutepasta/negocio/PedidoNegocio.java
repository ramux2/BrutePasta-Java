package brutepasta.negocio;

import java.util.List;

import brutepasta.entidades.Item;
import brutepasta.entidades.Produto;

public class PedidoNegocio {

    public static float calcularSubTotal(Item item, Produto produto) {
        return produto.getPreco() * item.getQuantidade();
    }
    public static float calcularTotal(List<Item> itens, Produto produto) {
        float total = 0;
        for(Item item: itens) {
            total += calcularSubTotal(item, produto);
        }
        return total;
    }
}
