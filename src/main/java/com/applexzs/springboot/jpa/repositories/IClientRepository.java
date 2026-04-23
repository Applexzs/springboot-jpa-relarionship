package com.applexzs.springboot.jpa.repositories;

import com.applexzs.springboot.jpa.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClientRepository extends CrudRepository<Client, Long> {
    @Query("select c from Client c left join fetch c.addresses where c.id = ?1")
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id = ?1")
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses left join fetch c.clientDetails where c.id = ?1")
    Optional<Client> findOne(Long id);
}
