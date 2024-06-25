package com.soltel.best_travel.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.results.graph.Fetch;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="reservation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationEntity  implements Serializable {

    @Id
   private UUID id;

    @Column(name ="date_reservation")
    private LocalDateTime dateTimeReservation;

    @Column(name ="date_start")
    private LocalDate dateStart;

    @Column(name ="date_end")
    private LocalDate dateEnd;

    @Column(name ="total_days")
    private Integer totalDays;
    private BigDecimal price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_id")
    private HotelEntity hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tour_id", nullable = true)
    private TourEntity tour;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="customer_id")
   private CustomerEntity customer;
}
