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

    public static Cliente procurarPorNome(TipoProduto tipoProduto) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from TipoProduto where nome = :param");
        consulta.setParameter("param", tipoProduto.getNome());
        List<Cliente> clientes = consulta.getResultList();
        if (!clientes.isEmpty()) {
            return clientes.get(0);
        }
        return null;
    }

    public static List<Cliente> getClientes(Cliente cliente) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Cliente where nome like :param");
        consulta.setParameter("param", "%" + cliente.getNome() + "%");
        List<Cliente> clientes = consulta.getResultList();
        return clientes;
    }
}