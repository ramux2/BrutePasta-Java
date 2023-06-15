package brutepasta.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho", fetch = FetchType.LAZY)
    private List<Item> itens;

    public void limparCarrinho() {
        itens.clear();
    }

    public void transferirParaPedido(Pedido pedido) {
        for (Item item : itens) {
            pedido.adicionarItem(item);
        }
        itens.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
