package brutepasta.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import brutepasta.entidades.Produto;

import java.util.List;

public class ProdutoPersistencia {
    public static boolean incluir(Produto produto) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(produto);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Produto> getProdutos(String tipo) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("FROM Produto WHERE tipo = :param");
        consulta.setParameter("param", tipo);
        List<Produto> produtos = consulta.getResultList();
        return produtos;
    }
}
