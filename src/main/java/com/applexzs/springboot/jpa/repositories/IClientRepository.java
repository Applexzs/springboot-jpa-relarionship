package com.applexzs.springboot.jpa.repositories;

import com.applexzs.springboot.jpa.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClientRepository extends CrudRepository<Client, Long> {
    @Query("select c from Client c join fetch c.addresses")
    Optional<Client> findOne(Long id);
}
