package com.soltel.best_travel.api.controller;


import com.soltel.best_travel.api.models.request.ReservationRequest;
import com.soltel.best_travel.api.models.request.TicketRequest;
import com.soltel.best_travel.api.models.responses.ReservationResponse;
import com.soltel.best_travel.api.models.responses.TicketResponse;
import com.soltel.best_travel.infraestructure.abstract_service.IReservationService;
import com.soltel.best_travel.infraestructure.abstract_service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path="reservation")
@AllArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));
    }

    @GetMapping(path="{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(reservationService.read(id));
    }
    @PutMapping(path="{id}")
    public ResponseEntity<ReservationResponse> update(@PathVariable UUID id, @RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.update(request, id));
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getHotelPrice(@RequestParam Long hotelId){
        return ResponseEntity.ok(Collections.singletonMap("hotelPrice", this.reservationService.findPriceHotel(hotelId)));
    }
}
