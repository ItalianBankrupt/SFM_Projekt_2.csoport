package com.spa.demo;

import com.spa.demo.backend.Restaurant;
import com.spa.demo.backend.RestaurantRepository;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.spa.demo.backend")
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
		try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csv/services.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String name = values[0];
				int price = Integer.parseInt(values[1]);
				String type = values[2];

				Services service = Services.builder()
						.name(name)
						.price(price)
						.type(type)
						.build();

				servicesRepository.save(service);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csv/restaurant.csv"))) {
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
			throw new RuntimeException(e);
		}
		System.out.printf("----------Szolgaltatasok----------");
		//Szolgáltatások lekérdezése az adatbázisból
		List<Services> szolgaltatasok = servicesRepository.findByType("Szolgaltatas");
		for (Services service : szolgaltatasok) {
			System.out.println(service.getName());
		}
		System.out.println("----------Belepok----------");
		//Belépőjegyek lekérdezése az adatbázisból
		List<Services> belepok = servicesRepository.findByType("Belepo");
		for (Services service : belepok) {
			System.out.println(service.getName());
		}
		System.out.println("----------Foetelek----------");
		//Foetelek lekérdezése az adatbázisból
		List<Restaurant> foetelek = restaurantRepository.findByType("Foetel");
		for (Restaurant restaurant : foetelek) {
			System.out.println(restaurant.getName());
		}

	}
}
