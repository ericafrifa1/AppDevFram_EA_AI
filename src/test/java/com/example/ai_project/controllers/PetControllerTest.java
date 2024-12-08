package com.example.ai_project.controllers;

import com.example.myapp.entities.Pet;
import com.example.myapp.services.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    public PetControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPets() {
        when(petService.getAllPets()).thenReturn(
                List.of(new Pet(1L, "Buddy", "Dog", "Labrador", 3, null))
        );

        List<Pet> result = petController.getAllPets();
        assertEquals(1, result.size());
        assertEquals("Buddy", result.get(0).getName());
    }

    @Test
    void testCreatePet() {
        Pet newPet = new Pet(null, "Whiskers", "Cat", "Siamese", 2, null);
        when(petService.createPet(newPet)).thenReturn(
                new Pet(2L, "Whiskers", "Cat", "Siamese", 2, null)
        );

        Pet result = petController.createPet(newPet);
        assertEquals(2L, result.getId());
    }

    @Test
    void testGetPetById() {
        Pet pet = new Pet(1L, "Buddy", "Dog", "Labrador", 3, null);
        when(petService.getPetById(1L)).thenReturn(pet);

        Pet result = petController.getPetById(1L);
        assertEquals("Buddy", result.getName());
    }
}
