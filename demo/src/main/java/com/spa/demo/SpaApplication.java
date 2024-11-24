package com.spa.demo;

import com.spa.demo.backend.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpaApplication implements CommandLineRunner {

	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//----------Szolgáltatások és jegyek beolvasása----
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csv/services.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String name = values[0];
				int price = Integer.parseInt(values[1]);
				String type = values[2];
				int ticketType = Integer.parseInt(values[3]);

				Services service = Services.builder()
						.name(name)
						.price(price)
						.type(type)
						.ticketType(ticketType)
						.build();

				servicesRepository.save(service);
			}
		} catch (Exception e) {
			throw new RuntimeException("Hiba történt a szolgáltatások beolvasásakor", e);
		}
		//-----------Ételek, italok és üdítők beolvasása---
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csv/restaurant.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String name = values[0];
				int price = Integer.parseInt(values[1]);
				String type = values[2];

				Restaurant restaurant = Restaurant.builder()
						.name(name)
						.price(price)
						.type(type)
						.build();

				restaurantRepository.save(restaurant);
			}
		} catch (Exception e) {
			throw new RuntimeException("Hiba történt a vendéglátóhelyek beolvasásakor", e);
		}
	}
}
