package com.soltel.best_travel.domain.repositories;

import com.soltel.best_travel.domain.entities.CustomerEntity;
import com.soltel.best_travel.domain.entities.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

}
