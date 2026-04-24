package com.applexzs.springboot.jpa;

import com.applexzs.springboot.jpa.entities.*;
import com.applexzs.springboot.jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IClientDetailsRepository clientDetailsRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ICourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        manyToManyRemoveBidireccionalFind();
    }

    @Transactional
    public void manyToManyRemoveBidireccionalFind() {
        Optional<Student> optionalStudent1 = studentRepository.findOneWtihCourses(1L);
        Optional<Student> optionalStudent2 = studentRepository.findOneWtihCourses(2L);

        Student student2 = optionalStudent2.get();
        Student student1 = optionalStudent1.get();

        Course course1 = courseRepository.findOneWithStudents(1L).get();
        Course course2 = courseRepository.findOneWithStudents(2L).get();

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course2);

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> studentOptionalDb = studentRepository.findOneWtihCourses(1L);
        if (studentOptionalDb.isPresent()) {
            Student studentDb = studentOptionalDb.get();
            Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.removeCourse(courseDb);
                studentRepository.save(studentDb);
                System.out.println(studentDb);
            }
        }
    }

    @Transactional
    public void manyToManyBidireccionalFind() {
        Optional<Student> optionalStudent1 = studentRepository.findOneWtihCourses(1L);
        Optional<Student> optionalStudent2 = studentRepository.findOneWtihCourses(2L);

        Student student2 = optionalStudent2.get();
        Student student1 = optionalStudent1.get();

        Course course1 = courseRepository.findOneWithStudents(1L).get();
        Course course2 = courseRepository.findOneWithStudents(2L).get();

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course2);

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);
    }

    @Transactional
    public void manyToManyBidireccionalRemove() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso Java Master", "Andres");
        Course course2 = new Course("Curso SpringBoot", "Andres");

//        student1.setCourses(Set.of(course1, course2));
//        student2.setCourses(Set.of(course2));
        student1.addCourse(course1);
        student1.addCourse(course2);

        student2.addCourse(course2);

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> studentOptionalDb = studentRepository.findOneWtihCourses(3L);
        if (studentOptionalDb.isPresent()) {
            Student studentDb = studentOptionalDb.get();
            Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.removeCourse(courseDb);
                studentRepository.save(studentDb);
                System.out.println(studentDb);
            }
        }
    }


    @Transactional
    public void manyToManyBidireccional() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso Java Master", "Andres");
        Course course2 = new Course("Curso SpringBoot", "Andres");

//        student1.setCourses(Set.of(course1, course2));
//        student2.setCourses(Set.of(course2));
        student1.addCourse(course1);
        student1.addCourse(course2);

        student2.addCourse(course2);

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);

    }


    @Transactional
    public void manyToManyRemove() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso Java Master", "Andres");
        Course course2 = new Course("Curso SpringBoot", "Andres");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> studentOptionalDb = studentRepository.findOneWtihCourses(3L);
        if (studentOptionalDb.isPresent()) {
            Student studentDb = studentOptionalDb.get();
            Optional<Course> courseOptionalDb = courseRepository.findById(3L);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.getCourses().remove(courseDb);
                studentRepository.save(studentDb);
                System.out.println(studentDb);
            }
        }
    }

    @Transactional
    public void manyToManyRemoveFind() {
        Optional<Student> optionalStudent1 = studentRepository.findById(1L);
        Optional<Student> optionalStudent2 = studentRepository.findById(2L);

        Student student2 = optionalStudent2.get();
        Student student1 = optionalStudent1.get();

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> studentOptionalDb = studentRepository.findOneWtihCourses(1L);
        if (studentOptionalDb.isPresent()) {
            Student studentDb = studentOptionalDb.get();
            Optional<Course> courseOptionalDb = courseRepository.findById(2L);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.getCourses().remove(courseDb);
                studentRepository.save(studentDb);
                System.out.println(studentDb);
            }
        }
    }

    @Transactional
    public void manyToManyFind() {
        Optional<Student> optionalStudent1 = studentRepository.findById(1L);
        Optional<Student> optionalStudent2 = studentRepository.findById(2L);

        Student student2 = optionalStudent2.get();
        Student student1 = optionalStudent1.get();

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);
    }

    @Transactional
    public void manyToMany() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso Java Master", "Andres");
        Course course2 = new Course("Curso SpringBoot", "Andres");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));
        System.out.println(student1);
        System.out.println(student2);
    }

    @Transactional
    public void oneToOneBidireccionalFindById() {
        Optional<Client> optionalClient = clientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {
            ClientDetails clientDetails = new ClientDetails(true, 5000);
            client.setClientDetails(clientDetails);
            clientDetails.setClient(client);
            clientRepository.save(client);
            System.out.println(client);
        });


    }

    @Transactional
    public void oneToOneBidireccional() {
        Client client = new Client("Erba", "Pura");
        ClientDetails clientDetails = new ClientDetails(true, 5000);

        client.setClientDetails(clientDetails);
        clientDetails.setClient(client);
        clientRepository.save(client);


        System.out.println(client);
    }


    @Transactional
    public void oneToOneFindById() {
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        clientDetailsRepository.save(clientDetails);

        Optional<Client> clientOptional = clientRepository.findById(2L);  //new Client("Erba", "Pura");
        clientOptional.ifPresent(client -> {
            client.setClientDetails(clientDetails);
            clientRepository.save(client);

            System.out.println(client);

        });

    }

    @Transactional
    public void oneToOne() {
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        clientDetailsRepository.save(clientDetails);

        Client client = new Client("Erba", "Pura");
        client.setClientDetails(clientDetails);
        clientRepository.save(client);

        System.out.println(client);

    }

    @Transactional
    public void removeInvoiceBidireccionalFinById() {
        Optional<Client> optionalClient = clientRepository.findOne(1L);

        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

            Set<Invoice> invoices = new HashSet<>();
            invoices.add(invoice1);
            invoices.add(invoice2);
            client.setInvoices(invoices);

            invoice1.setClient(client);
            invoice2.setClient(client);
            clientRepository.save(client);

            System.out.println(client);

        });

        Optional<Client> optionalClientDb = clientRepository.findOne(1L);
        optionalClientDb.ifPresent(client -> {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
            invoiceOptional.ifPresent(invoice -> {
                client.getInvoices().remove(invoice);
                invoice.setClient(null);
                clientRepository.save(client);
                System.out.println(client);
            });
        });
    }


    @Transactional
    public void oneToManyInvoiceBidireccionalFinById() {
        Optional<Client> optionalClient = clientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

            Set<Invoice> invoices = new HashSet<>();
            invoices.add(invoice1);
            invoices.add(invoice2);
            client.setInvoices(invoices);

            invoice1.setClient(client);
            invoice2.setClient(client);
            clientRepository.save(client);

            System.out.println(client);

        });

    }

    @Transactional
    public void oneToManyInvoiceBidireccional() {
        Client client = new Client("Fran", "Moras");

        Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
        Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

        Set<Invoice> invoices = new HashSet<>();
        invoices.add(invoice1);
        invoices.add(invoice2);

        client.setInvoices(invoices);

        invoice1.setClient(client);
        invoice2.setClient(client);

        clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void removeAddressFindById() {
        Optional<Client> optionalClient = clientRepository.findById(2L);
        optionalClient.ifPresent(client -> {
            Address address1 = new Address("El verjel", 1234);
            Address address2 = new Address("Vasco de Gama", 9875);

            Set<Address> adresses = new HashSet<>();
            adresses.add(address1);
            adresses.add(address2);

            client.setAddresses(adresses);

            clientRepository.save(client);

            System.out.println(client);

            Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
            optionalClient2.ifPresent(c -> {
                c.getAddresses().remove(address2);
                clientRepository.save(c);
                System.out.println(c);
            });
        });


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

            Set<Address> adresses = new HashSet<>();
            adresses.add(address1);
            adresses.add(address2);
            client.setAddresses(adresses);

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
