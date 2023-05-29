package brutepasta.negocio;

import brutepasta.entidades.Item;
import brutepasta.entidades.Produto;
public class ItemNegocio {
    public static float calcularSubTotal(Item item, Produto produto) {
        return produto.getPreco() * item.getQuantidade();
    }
}
