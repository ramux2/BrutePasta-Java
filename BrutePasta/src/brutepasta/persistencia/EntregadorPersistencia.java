package brutepasta.persistencia;

import brutepasta.entidades.Cliente;
import brutepasta.entidades.Entregador;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntregadorPersistencia {
    public static boolean incluir(Entregador entregador) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(entregador);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Entregador entregador) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(entregador);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Entregador entregador) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(entregador);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Entregador procurarPorPlaca(Entregador entregador) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Entregador where placaVeiculo = :param");
        consulta.setParameter("param", entregador.getPlacaVeiculo());
        List<Entregador> entregadores = consulta.getResultList();
        if (!entregadores.isEmpty()) {
            return entregadores.get(0);
        }
        return null;
    }

    public static Entregador procurarPorNome(Entregador entregador) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Entregador where nome = :param");
        consulta.setParameter("param", entregador.getNome());
        List<Entregador> entregadores = consulta.getResultList();
        if (!entregadores.isEmpty()) {
            return entregadores.get(0);
        }
        return null;
    }

    public static List<Entregador> getEntregadores() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("select tp From Entregador tp");
        List<Entregador> entregadores = consulta.getResultList();
        return entregadores;
    }
}
