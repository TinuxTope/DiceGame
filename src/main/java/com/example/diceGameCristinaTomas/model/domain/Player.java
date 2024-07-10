package com.example.diceGameCristinaTomas.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Player(String name, Role role) {
        this.name = name.isEmpty() ? "ANONIMUS" : name;
        this.registrationDate = LocalDate.now();
        this.role = role;
    }



    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
