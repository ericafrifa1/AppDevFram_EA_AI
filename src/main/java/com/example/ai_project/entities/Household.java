package com.example.myapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "households")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Eircode is mandatory")
    private String eircode;

    @Min(value = 0, message = "Number of occupants must be non-negative")
    private int numberOfOccupants;

    @Min(value = 0, message = "Maximum number of occupants must be non-negative")
    private int maxNumberOfOccupants;

    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;
}
