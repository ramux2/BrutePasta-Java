package brutepasta.persistencia;

import brutepasta.entidades.Cliente;
import brutepasta.entidades.TipoProduto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TipoProdutoPersistencia {
    public static boolean incluir(TipoProduto tipoProduto) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tipoProduto);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(TipoProduto tipoProduto) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tipoProduto);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(TipoProduto tipoProduto) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(tipoProduto);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static TipoProduto procurarPorNome(TipoProduto tipoProduto) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from TipoProduto where nome like :param");
        consulta.setParameter("param", tipoProduto.getNome());
        List<TipoProduto> tipoProdutos = consulta.getResultList();
        if (!tipoProdutos.isEmpty()) {
            return tipoProdutos.get(0);
        }
        return null;
    }

    public static List<TipoProduto> getTipoProduto() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("select tp from TipoProduto tp");
        List<TipoProduto> tipoProdutos = consulta.getResultList();
        return tipoProdutos;
    }

}