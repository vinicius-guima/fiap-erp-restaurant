package br.com.fiap.tech.challenge.erp_restaurant.application.domain;

import br.com.fiap.tech.challenge.erp_restaurant.shared.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private Role role;

    public User(Long id, String name, String email, String login, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role == null ? Role.CUSTOMER : role;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

}
