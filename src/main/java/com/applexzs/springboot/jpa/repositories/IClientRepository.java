package com.applexzs.springboot.jpa.repositories;

import com.applexzs.springboot.jpa.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientRepository extends CrudRepository<Client, Long> {


}
