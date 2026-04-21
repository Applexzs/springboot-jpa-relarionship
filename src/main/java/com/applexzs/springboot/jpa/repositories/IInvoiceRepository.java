package com.applexzs.springboot.jpa.repositories;

import com.applexzs.springboot.jpa.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface IInvoiceRepository extends CrudRepository<Invoice, Long> {
}
