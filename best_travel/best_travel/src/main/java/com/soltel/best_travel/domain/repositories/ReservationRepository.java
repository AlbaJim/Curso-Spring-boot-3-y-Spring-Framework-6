package com.soltel.best_travel.domain.repositories;

import com.soltel.best_travel.domain.entities.FlyEntity;
import com.soltel.best_travel.domain.entities.HotelEntity;
import com.soltel.best_travel.domain.entities.ReservationEntity;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {



}
