package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ClientFactory {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public ClientFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("mohr");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Client getById(int id){
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, id);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return client;
    }

    public void add (Client client){
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    public void remove (Client client){
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAll(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT clients FROM Client clients");
        List<Client> clients = query.getResultList();
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return clients;
    }
}
