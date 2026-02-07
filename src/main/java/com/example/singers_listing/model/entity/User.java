package com.example.singers_listing.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name="singers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @Column(name = "style", nullable = false)
    private String style;
}