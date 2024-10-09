package org.example.Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaServicesDAO implements ServicesDAO {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveService(Services service) {
        entityManager.getTransaction().begin();
        entityManager.persist(service);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Services> getAllServices() {
        entityManager.getTransaction().begin();
        TypedQuery<Services> query = entityManager.createQuery("SELECT s FROM Services s", Services.class);
        List<Services> services = query.getResultList();
        entityManager.getTransaction().commit();
        return services;
    }

    @Override
    public void updateService(Services service) {
        entityManager.getTransaction().begin();
        entityManager.persist(service);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteService(Services service) {
        entityManager.getTransaction().begin();
        entityManager.remove(service);
        entityManager.getTransaction().commit();
    }
}
