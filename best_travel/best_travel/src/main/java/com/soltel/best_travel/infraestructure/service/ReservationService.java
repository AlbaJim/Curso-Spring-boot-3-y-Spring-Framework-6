package com.soltel.best_travel.infraestructure.service;

import com.soltel.best_travel.api.models.request.ReservationRequest;
import com.soltel.best_travel.api.models.responses.FlyResponse;
import com.soltel.best_travel.api.models.responses.HotelResponse;
import com.soltel.best_travel.api.models.responses.ReservationResponse;
import com.soltel.best_travel.api.models.responses.TicketResponse;
import com.soltel.best_travel.domain.entities.ReservationEntity;
import com.soltel.best_travel.domain.entities.TicketEntity;
import com.soltel.best_travel.domain.repositories.*;
import com.soltel.best_travel.infraestructure.abstract_service.IReservationService;
import com.soltel.best_travel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.20);

    @Override
    public BigDecimal findPriceHotel(Long hotelId) {
        var hotel = this.hotelRepository.findById(hotelId).orElseThrow();
        return hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage));
    }

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .dateStart(LocalDate.now())
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage)))
                .build();

        var reservationPersisted = this.reservationRepository.save(reservationToPersist);
        log.info("Reservation saved with id: {}", reservationPersisted.getId());
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        var response = this.reservationRepository.findById(uuid).orElseThrow();

        return this.entityToResponse(response);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var reservationToUpdate = this.reservationRepository.findById(id).orElseThrow();
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage)));
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setTotalDays(request.getTotalDays());


        var reservationUpdate = this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation update whit id {}", reservationUpdate.getId());


        return entityToResponse(reservationUpdate);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = this.reservationRepository.findById(id).orElseThrow();
        this.reservationRepository.delete(ticketToDelete);
    }

    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }
}
