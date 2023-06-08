package brutepasta.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import brutepasta.entidades.Produto;

import brutepasta.entidades.Cliente;
import brutepasta.entidades.Produto;
import brutepasta.entidades.TipoProduto;
import org.hibernate.internal.build.AllowSysOut;

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

    public static Produto procurarPorNome(Produto produto) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Produto where nome = :param");
        consulta.setParameter("param", produto.getNome());
        List<Produto> produtos = consulta.getResultList();
        if (!produtos.isEmpty()) {
            return produtos.get(0);
        }
        return null;
    }

    public static boolean alterar(Produto Produto) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(Produto);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Produto produto) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(produto);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Produto> getProdutos(Produto produto) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Produto where nome like :param");
        consulta.setParameter("param", "%" + produto.getNome() + "%");
        List<Produto> produtos = consulta.getResultList();
        return produtos;
    }
}


