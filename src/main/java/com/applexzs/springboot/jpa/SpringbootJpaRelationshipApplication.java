package com.applexzs.springboot.jpa;

import com.applexzs.springboot.jpa.entities.Address;
import com.applexzs.springboot.jpa.entities.Client;
import com.applexzs.springboot.jpa.entities.Invoice;
import com.applexzs.springboot.jpa.repositories.IClientRepository;
import com.applexzs.springboot.jpa.repositories.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

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
        removeAddress();
    }

    @Transactional
    public void removeAddress() {
        Client client = new Client("Fran", "Moras");
        Address address1 = new Address("El verjel", 1234);
        Address address2 = new Address("Vasco de Gama", 9875);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);
        clientRepository.save(client);
        System.out.println(client);

        Optional<Client> optionalClient = clientRepository.findById(3L);
        optionalClient.ifPresent(c -> {
            c.getAddresses().remove(address1);
            clientRepository.save(c);
            System.out.println(c);
        });
    }

    @Transactional
    public void oneToManyFindById() {
        Optional<Client> optionalClient = clientRepository.findById(2L);
        optionalClient.ifPresent(client -> {
            Address address1 = new Address("El verjel", 1234);
            Address address2 = new Address("Vasco de Gama", 9875);

            client.setAddresses(Arrays.asList(address1, address2));

            clientRepository.save(client);

            System.out.println(client);
        });


    }


    @Transactional
    public void oneToMany() {
        Client client = new Client("Fran", "Moras");
        Address address1 = new Address("El verjel", 1234);
        Address address2 = new Address("Vasco de Gama", 9875);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);
        clientRepository.save(client);
        System.out.println(client);
    }

    @Transactional
    public void manyToOne() {
        Client client = new Client("John", "Doe");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2200L);
        invoice.setClient(client);
        Invoice invoiceDB = invoiceRepository.save(invoice);
        System.out.println(invoiceDB);
    }

    @Transactional
    public void manyToOneFindByIdClient() {
        Optional<Client> optionalClient = clientRepository.findById(1L);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.orElseThrow();
            Invoice invoice = new Invoice("Compras de oficina", 2200L);
            invoice.setClient(client);
            Invoice invoiceDB = invoiceRepository.save(invoice);
            System.out.println(invoiceDB);
        }

    }
}
