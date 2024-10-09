package org.example;

public class RestaurantUtils {
    private RestaurantDAO rDAO;

    public void setrDAO(RestaurantDAO rDAO) {
        this.rDAO = rDAO;
    }

    public void runUtils(){
        Restaurant test = new Restaurant("Test",1000,Restaurant.Type.Foetel);
        rDAO.saveRestaurant(test);
    }
}
