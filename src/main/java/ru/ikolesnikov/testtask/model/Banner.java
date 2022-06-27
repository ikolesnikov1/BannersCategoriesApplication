package ru.ikolesnikov.testtask.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;

    @NotEmpty(message = "Name field must be not empty.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Price field must be not equal to zero.")
    @Column(columnDefinition = "decimal(8,2)", nullable = false)
    private Double price;

    @NotEmpty(message = "Text field must be not empty.")
    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean deleted;

    @NotNull(message = "Category field must be not empty.")
    @NotEmpty(message = "Category field must be not empty.")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}