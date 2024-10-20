package org.example.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestaurantUtils {
    private RestaurantDAO rDAO;

    public void setrDAO(RestaurantDAO rDAO) {
        this.rDAO = rDAO;
    }

    public void runUtils(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/org/restaurant.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int price = Integer.parseInt(values[1]);
                Restaurant.Type type = Restaurant.Type.valueOf(values[2]);
                Restaurant restaurant = new Restaurant(name,price,type);
                rDAO.saveRestaurant(restaurant);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
