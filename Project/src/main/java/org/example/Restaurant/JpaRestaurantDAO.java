package org.example.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaRestaurantDAO implements RestaurantDAO {

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        entityManager.getTransaction().begin();
        entityManager.persist(restaurant);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        entityManager.getTransaction().begin();
        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
        List<Restaurant> restaurants = query.getResultList();
        entityManager.getTransaction().commit();
        return restaurants;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        entityManager.getTransaction().begin();
        entityManager.persist(restaurant);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        entityManager.getTransaction().begin();
        entityManager.remove(restaurant);
        entityManager.getTransaction().commit();
    }
}
