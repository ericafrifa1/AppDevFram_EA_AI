package com.example.ai_project.controllers;

import com.example.myapp.entities.Pet;
import com.example.myapp.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public Pet createPet(@Valid @RequestBody Pet pet) {
        return petService.createPet(pet);
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @Valid @RequestBody Pet pet) {
        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{animalType}")
    public List<Pet> findPetsByAnimalType(@PathVariable String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @GetMapping("/breed/{breed}")
    public List<Pet> findPetsByBreed(@PathVariable String breed) {
        return petService.findPetsByBreed(breed);
    }

    @GetMapping("/names-and-breeds")
    public List<Object[]> getNameAndBreed() {
        return petService.getNameAndBreed();
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getPetStatistics() {
        double averageAge = petService.getAverageAge();
        int oldestAge = petService.getOldestAge();
        return ResponseEntity.ok(Map.of("averageAge", averageAge, "oldestAge", oldestAge));
    }
}

