package com.example.ai_project.services;

import com.example.myapp.entities.Pet;
import com.example.myapp.repositories.PetRepository;
import com.example.myapp.exceptions.PetNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPetById_PetExists() {
        Pet pet = new Pet(1L, "Buddy", "Dog", "Labrador", 3, null);
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Pet result = petService.getPetById(1L);
        assertEquals("Buddy", result.getName());
    }

    @Test
    void testGetPetById_PetNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> {
            petService.getPetById(1L);
        });
    }
}
