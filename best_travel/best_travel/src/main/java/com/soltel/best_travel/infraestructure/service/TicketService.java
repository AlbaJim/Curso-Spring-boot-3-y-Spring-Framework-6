package com.soltel.best_travel.infraestructure.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.soltel.best_travel.api.models.request.TicketRequest;
import com.soltel.best_travel.api.models.responses.FlyResponse;
import com.soltel.best_travel.api.models.responses.TicketResponse;
import com.soltel.best_travel.domain.entities.TicketEntity;
import com.soltel.best_travel.domain.repositories.CustomerRepository;
import com.soltel.best_travel.domain.repositories.FlyRepository;
import com.soltel.best_travel.domain.repositories.TicketRepository;
import com.soltel.best_travel.infraestructure.abstract_service.ITicketService;
import com.soltel.best_travel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);

    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly = this.flyRepository.findById(flyId).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));
    }

    @Override
    public TicketResponse create(TicketRequest request) {

        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(BestTravelUtil.getRandomLatter())
                .departureDate(BestTravelUtil.getRandomSoon())
                .build();

        var ticketPersisted = this.ticketRepository.save(ticketToPersist);
        log.info("Ticket saved with id: {}", ticketPersisted.getId());
        return this.entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID uuid) {
        var response = this.ticketRepository.findById(uuid).orElseThrow();

        return this.entityToResponse(response);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
       var ticketToUpdate = this.ticketRepository.findById(id).orElseThrow();
       var fly = this.flyRepository.findById(request.getIdFly()).orElseThrow();

       ticketToUpdate.setFly(fly);
       ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)));
       ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
       ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLatter());

       var ticketUpdate = this.ticketRepository.save(ticketToUpdate);
       log.info("Ticket update whit id {}", ticketUpdate.getId());


        return entityToResponse(ticketUpdate);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = this.ticketRepository.findById(id).orElseThrow();
        this.ticketRepository.delete(ticketToDelete);
    }


    private TicketResponse entityToResponse(TicketEntity entity){
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity, response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }


}
