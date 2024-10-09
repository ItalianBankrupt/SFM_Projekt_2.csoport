package org.example;

import java.util.List;

public interface RestaurantDAO {
    public void saveRestaurant(Restaurant restaurant);
    public List<Restaurant> getAllRestaurants();
    public void updateRestaurant(Restaurant restaurant);
    public void deleteRestaurant(Restaurant restaurant);
}
