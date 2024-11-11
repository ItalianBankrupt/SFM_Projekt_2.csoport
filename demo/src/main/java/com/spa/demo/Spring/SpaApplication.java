package com.spa.demo.Spring;

import com.spa.demo.backend.Restaurant;
import com.spa.demo.backend.RestaurantRepository;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import java.io.BufferedReader;
import java.io.FileReader;

@SpringBootApplication(scanBasePackages = {"com.spa.demo.backend"})
@EntityScan("com.spa.demo.backend")
public class SpaApplication implements CommandLineRunner {

	@Autowired
	ServicesRepository servicesRepository;

	@Autowired
	RestaurantRepository restaurantRepository;
	public static void main(String[] args) {

		SpringApplication.run(SpaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csv/services.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				String name = split[0];
				int price = Integer.parseInt(split[1]);
				Services.Type type = Services.Type.valueOf(split[2]);
				Services service = Services.builder().item(name).price(price).type(type).build();
				servicesRepository.save(service);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}


		try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csv/restaurant.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				String name = split[0];
				int price = Integer.parseInt(split[1]);
				Restaurant.Type type = Restaurant.Type.valueOf(split[2]);
				Restaurant restaurant = Restaurant.builder().item(name).price(price).type(type).build();
				restaurantRepository.save(restaurant);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
