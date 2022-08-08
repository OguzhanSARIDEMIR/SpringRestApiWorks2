package com.works.days5.entities;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int uid;
    private String name;
    private String surname;

    @Column(unique = true, length = 150)
    private String email;

    private int age;

    @Column(length = 32)
    private String password;

}
