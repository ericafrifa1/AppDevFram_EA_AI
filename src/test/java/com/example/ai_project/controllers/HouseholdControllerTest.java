package com.example.ai_project.controllers;

import com.example.myapp.entities.Household;
import com.example.myapp.services.HouseholdService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HouseholdControllerTest {

    @Mock
    private HouseholdService householdService;

    @InjectMocks
    private HouseholdController householdController;

    public HouseholdControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllHouseholds() {
        when(householdService.getAllHouseholds()).thenReturn(
                List.of(new Household(1L, "A12BCD", 2, 5, true, null))
        );

        List<Household> result = householdController.getAllHouseholds();
        assertEquals(1, result.size());
        assertEquals("A12BCD", result.get(0).getEircode());
    }

    @Test
    void testGetHouseholdById() {
        Household household = new Household(1L, "A12BCD", 2, 5, true, null);
        when(householdService.getHouseholdById(1L, true)).thenReturn(household);

        Household result = householdController.getHouseholdById(1L, true);
        assertEquals("A12BCD", result.getEircode());
    }

    @Test
    void testCreateHousehold() {
        Household newHousehold = new Household(null, "E34FGH", 0, 3, false, null);
        when(householdService.createHousehold(newHousehold)).thenReturn(
                new Household(2L, "E34FGH", 0, 3, false, null)
        );

        Household result = householdController.createHousehold(newHousehold);
        assertEquals(2L, result.getId());
    }
}
