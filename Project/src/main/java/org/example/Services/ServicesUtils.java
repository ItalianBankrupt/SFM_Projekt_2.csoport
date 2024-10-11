package org.example.Services;

import org.example.Restaurant.Restaurant;
import org.example.Restaurant.RestaurantDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServicesUtils {
    private ServicesDAO rDAO;

    public void setrDAO(ServicesDAO rDAO) {
        this.rDAO = rDAO;
    }

    public void runUtils(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/org/services.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int price = Integer.parseInt(values[1]);
                Services.Type type = Services.Type.valueOf(values[2]);
                Services services = new Services(name,price,type);
                rDAO.saveService(services);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
