package com.soltel.best_travel.infraestructure.abstract_service;

import com.soltel.best_travel.api.models.request.ReservationRequest;
import com.soltel.best_travel.api.models.responses.ReservationResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID>{
    BigDecimal findPriceHotel(Long hotelId);
}
