package com.applexzs.springboot.jpa;

import com.applexzs.springboot.jpa.entities.Client;
import com.applexzs.springboot.jpa.entities.Invoice;
import com.applexzs.springboot.jpa.repositories.IClientRepository;
import com.applexzs.springboot.jpa.repositories.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IInvoiceRepository invoiceRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        manyToOne();
    }

    public void manyToOne() {
        Client client = new Client("John", "Doe");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2200L);
        invoice.setClient(client);
        Invoice invoiceDB = invoiceRepository.save(invoice);
        System.out.println(invoiceDB);
    }
}
