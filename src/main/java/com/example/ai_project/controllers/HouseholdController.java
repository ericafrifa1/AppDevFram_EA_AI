package com.example.myapp.controllers;

import com.example.myapp.entities.Household;
import com.example.myapp.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @PostMapping
    public Household createHousehold(@Valid @RequestBody Household household) {
        return householdService.createHousehold(household);
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @GetMapping("/{id}")
    public Household getHouseholdById(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean includePets) {
        return householdService.getHouseholdById(id, includePets);
    }

    @PutMapping("/{id}")
    public Household updateHousehold(@PathVariable Long id, @Valid @RequestBody Household household) {
        return householdService.updateHousehold(id, household);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable Long id) {
        householdService.deleteHouseholdById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-pets")
    public List<Household> findHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/owner-occupied")
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdService.findOwnerOccupiedHouseholds();
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getHouseholdStatistics() {
        long emptyHouses = householdService.countEmptyHouses();
        long fullHouses = householdService.countFullHouses();
        return ResponseEntity.ok(Map.of("emptyHouses", emptyHouses, "fullHouses", fullHouses));
    }
}
