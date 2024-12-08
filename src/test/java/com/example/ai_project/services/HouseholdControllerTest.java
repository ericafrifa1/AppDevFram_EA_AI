package com.example.ai_project.services;

package com.example.myapp.services;

import com.example.myapp.entities.Household;
import com.example.myapp.repositories.HouseholdRepository;
import com.example.myapp.exceptions.HouseholdNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class HouseholdServiceTest {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    public HouseholdServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHouseholdById_HouseholdExists() {
        Household household = new Household(1L, "A12BCD", 2, 5, true, null);
        when(householdRepository.findById(1L)).thenReturn(Optional.of(household));

        Household result = householdService.getHouseholdById(1L, true);
        assertEquals("A12BCD", result.getEircode());
    }

    @Test
    void testGetHouseholdById_HouseholdNotFound() {
        when(householdRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HouseholdNotFoundException.class, () -> {
            householdService.getHouseholdById(1L, true);
        });
    }
}
