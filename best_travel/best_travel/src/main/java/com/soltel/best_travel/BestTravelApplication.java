package com.soltel.best_travel;


import com.soltel.best_travel.domain.entities.ReservationEntity;
import com.soltel.best_travel.domain.entities.TicketEntity;
import com.soltel.best_travel.domain.entities.TourEntity;
import com.soltel.best_travel.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@SpringBootApplication
@Slf4j
public class BestTravelApplication implements CommandLineRunner {

	private final HotelRepository hotelRepository;

	private final FlyRepository flyRepository;
	private final CustomerRepository customerRepository;
	private final ReservationRepository reservationRepository;
	private final TicketRepository ticketRepository;
	private final TourRepository tourRepository;

    public BestTravelApplication(HotelRepository hotelRepository,
								 FlyRepository flyRepository,
								 CustomerRepository customerRepository,
								 ReservationRepository reservationRepository,
								 TicketRepository ticketRepository,
								 TourRepository tourRepository) {
        this.hotelRepository = hotelRepository;
        this.flyRepository = flyRepository;
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.tourRepository = tourRepository;
    }


    public static void main(String[] args) {
		SpringApplication.run(BestTravelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/*
		var fly = flyRepository.findById(15L).get();
		var hotel = hotelRepository.findById(7L).get();
		var ticket = ticketRepository.findById(UUID.fromString("22345678-1234-5678-3235-567812345678")).get();
		var reservation = reservationRepository.findById(UUID.fromString("52345678-1234-5678-1234-567812345678")).get();
		var customer = customerRepository.findById("VIKI771012HMCRG093").get();

		log.info(String.valueOf(fly));
		log.info(String.valueOf(hotel));
		log.info(String.valueOf(ticket));
		log.info(String.valueOf(reservation));
		log.info(String.valueOf(customer));

		this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(System.out::println);
		this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(10), BigDecimal.valueOf(15)).forEach(System.out::println);
		this.flyRepository.selectOriginDestiny("Grecia", "Mexico").forEach(System.out::println);

		var fly = flyRepository.findById(1L).get();
		fly.getTickets().forEach(ticket -> System.out.println(ticket));

		var hotel = hotelRepository.findByReservationId(UUID.fromString("12345678-1234-5678-1234-567812345678"));

		System.out.println(hotel);

//--------------------------------------------------------------------------------------------
		var customer = customerRepository.findById("GOTW771012HMRGR087").orElseThrow();
		log.info("Client name: " + customer.getFullName());

		var fly2 = flyRepository.findById(11L).orElseThrow();
		log.info("Vuelo: " + fly2.getOriginName() + "-"+ fly2.getDestinyName());

		var hotel2 = hotelRepository.findById(3L).orElseThrow();
		log.info("Hotel: " + hotel2.getName());

		var tour = TourEntity.builder().customer(customer).build();

		var ticket = TicketEntity.builder()
				.id(UUID.randomUUID())
				.price(fly2.getPrice().multiply(BigDecimal.TEN))
				.arrivalDate(LocalDateTime.now())
				.departureDate(LocalDateTime.now())
				.purchaseDate(LocalDate.now())
				.customer(customer)
				.tour(tour)
				.fly(fly2)
				.build();

		var reservation = ReservationEntity.builder()
				.id(UUID.randomUUID())
				.dateTimeReservation(LocalDate.now())
				.dateEnd(LocalDate.now().plusDays(2))
				.dateStart(LocalDate.now().plusDays(1))
				.hotel(hotel2)
				.customer(customer)
				.tour(tour)
				.totalDays(1)
				.price(hotel2.getPrice().multiply(BigDecimal.TEN))
				.build();


		tour.addReservation(reservation); //añadir reservas al set
		tour.updateReservations(); //para hacer la relación inversa

		tour.addTicket(ticket);
		tour.updateTickets();


		var tourSaved = this.tourRepository.save(tour);
		Thread.sleep(8000);
		this.tourRepository.deleteById(tourSaved.getId()); */
	}
}
