package brutepasta.persistencia;

import brutepasta.entidades.Pedido;
import brutepasta.entidades.TipoProduto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PedidoPersistencia {
    public static boolean incluir(Pedido pedido) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Pedido pedido) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Pedido pedido) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(pedido);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Pedido> getPedido() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("select tp from Pedido tp");
        List<Pedido> pedidos = consulta.getResultList();
        return pedidos;
    }
}
