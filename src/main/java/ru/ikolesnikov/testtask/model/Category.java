package ru.ikolesnikov.testtask.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotEmpty(message = "Name field must be not empty.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotEmpty(message = "Request ID field must be not empty.")
    @Column(nullable = false, unique = true)
    private String request;

    @Column(nullable = false)
    private Boolean deleted;

}