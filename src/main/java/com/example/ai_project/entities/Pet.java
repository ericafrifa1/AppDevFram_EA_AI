package com.example.myapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Animal type is mandatory")
    private String animalType;

    @NotBlank(message = "Breed is mandatory")
    private String breed;

    @Min(value = 0, message = "Age must be non-negative")
    private int age;

    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;
}
