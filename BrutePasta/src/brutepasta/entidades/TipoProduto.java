package brutepasta.entidades;

import javax.persistence.*;

@Entity
public class TipoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private static String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
