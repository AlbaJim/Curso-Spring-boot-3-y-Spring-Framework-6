package com.soltel.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import com.soltel.best_travel.util.AeroLine;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name="fly")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_lat")
    private Double originLat;

    @Column(name = "origin_lng")
    private Double originLng;

    @Column(name = "destiny_lat")
    private Double destinyLat;

    @Column(name = "destiny_lng")
    private Double destinyLng;

    @Column(name = "origin_name", length = 20)
    private String originName;

    @Column(name="destiny_name", length = 20)
    private String destinyName;

    private BigDecimal price;

    @Column(name="aero_line")
    @Enumerated(EnumType.STRING)
    private AeroLine aeroline;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "fly"
    )
    private Set<TicketEntity> tickets;

}
