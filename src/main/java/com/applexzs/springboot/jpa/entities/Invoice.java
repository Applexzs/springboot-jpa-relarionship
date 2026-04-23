package com.applexzs.springboot.jpa.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    @JoinColumn(name = "client_id")
    @ManyToOne
    private Client client;

    public Invoice() {
    }

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Invoice invoice)) return false;

        return Objects.equals(id, invoice.id) && Objects.equals(description, invoice.description) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(total);
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total  +
                '}';
    }
}
