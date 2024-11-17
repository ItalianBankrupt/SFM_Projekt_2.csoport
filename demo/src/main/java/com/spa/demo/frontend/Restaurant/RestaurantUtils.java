package com.spa.demo.frontend.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestaurantUtils {


    public void runUtils(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/org/restaurant.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int price = Integer.parseInt(values[1]);
                Restaurant.Type type = Restaurant.Type.valueOf(values[2]);
                Restaurant restaurant = new Restaurant(name,price,type);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
