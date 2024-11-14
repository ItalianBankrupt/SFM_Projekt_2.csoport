package com.spa.demo;

import com.spa.demo.backend.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private CupboardRepository cupboardRepository;

	@Autowired
	private IdentificationRepository identificationRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Beolvassuk a szolgáltatásokat
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

		// Beolvassuk a restaurant adatokat
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

		// Szolgáltatások lekérdezése
		System.out.println("----------Szolgáltatások----------");
		List<Services> szolgaltatasok = servicesRepository.findByType("Szolgaltatas");
		for (Services service : szolgaltatasok) {
			System.out.println(service.getName());
		}

		System.out.println("----------Belépők----------");
		List<Services> belepok = servicesRepository.findByType("Belepo");
		for (Services service : belepok) {
			System.out.println(service.getName());
		}

		System.out.println("----------Ételek----------");
		List<Restaurant> foetelek = restaurantRepository.findByType("Foetel");
		for (Restaurant restaurant : foetelek) {
			System.out.println(restaurant.getName());
		}

		System.out.println("----------Tesztelés----------");
		Registration buyer = new Registration();
		buyer.setName("Kovács János");
		buyer.setCity("Budapest");
		buyer.setStreet("Kossuth Lajos u.");
		buyer.setPostCode("1234");
		registrationRepository.save(buyer);

		List<Cupboard> szekrenyek = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Cupboard szekreny = Cupboard.builder()
					.cupboardNumber(i + 1)
					.registration(buyer)
					.build();
			cupboardRepository.save(szekreny);
			szekrenyek.add(szekreny);
		}

		List<Identification> identifications = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Services service = servicesRepository.findById(1L).orElseThrow();

			Identification identification = Identification.builder()
					.registration(buyer)
					.services(service)
					.darabszam(1)
					.ticketType(1)
					.build();

			identificationRepository.save(identification);
		}
	}
}
