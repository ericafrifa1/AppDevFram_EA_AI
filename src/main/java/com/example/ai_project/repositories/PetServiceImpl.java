package com.example.myapp.services;

import com.example.myapp.entities.Pet;
import com.example.myapp.repositories.PetRepository;
import com.example.myapp.exceptions.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));
    }

    @Override
    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = getPetById(id);
        pet.setName(petDetails.getName());
        pet.setAnimalType(petDetails.getAnimalType());
        pet.setBreed(petDetails.getBreed());
        pet.setAge(petDetails.getAge());
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("Pet not found");
        }
        petRepository.deleteById(id);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<Object[]> getNameAndBreed() {
        return petRepository.findAll()
                .stream()
                .map(pet -> new Object[]{pet.getName(), pet.getAnimalType(), pet.getBreed()})
                .toList();
    }

    @Override
    public double getAverageAge() {
        return petRepository.findAll()
                .stream()
                .mapToInt(Pet::getAge)
                .average()
                .orElse(0.0);
    }

    @Override
    public int getOldestAge() {
        return petRepository.findAll()
                .stream()
                .mapToInt(Pet::getAge)
                .max()
                .orElse(0);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> pets = petRepository.findByNameIgnoreCase(name);
        if (pets.isEmpty()) {
            throw new PetNotFoundException("No pets found with name: " + name);
        }
        petRepository.deleteAll(pets);
    }
}
