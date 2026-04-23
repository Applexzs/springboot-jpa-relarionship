package com.applexzs.springboot.jpa.repositories;

import com.applexzs.springboot.jpa.entities.ClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface IClientDetailsRepository extends CrudRepository<ClientDetails, Long> {

}
