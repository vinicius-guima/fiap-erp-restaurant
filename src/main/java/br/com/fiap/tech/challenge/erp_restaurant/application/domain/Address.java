package br.com.fiap.tech.challenge.erp_restaurant.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Address {

    private Long id;
    private String state;
    private String city;
    private String street;
    private int number;
    private String complement;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private User user;

    public Address(String state, String city, String street, int number, String complement, User user) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }
}
