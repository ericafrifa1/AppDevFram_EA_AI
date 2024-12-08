package com.example.myapp.services;

import com.example.myapp.entities.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet updatePet(Long id, Pet petDetails);
    void deletePetById(Long id);
    List<Pet> findPetsByAnimalType(String animalType);
    List<Pet> findPetsByBreed(String breed);
    List<Object[]> getNameAndBreed();
    double getAverageAge();
    int getOldestAge();
    void deletePetsByName(String name);
}
