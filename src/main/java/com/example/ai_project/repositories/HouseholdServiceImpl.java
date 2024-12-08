package com.example.myapp.services;

import com.example.myapp.entities.Household;
import com.example.myapp.repositories.HouseholdRepository;
import com.example.myapp.exceptions.HouseholdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdById(Long id, boolean includePets) {
        Household household = householdRepository.findById(id)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found"));
        if (!includePets) {
            household.setPets(null); // Exclude pets if requested
        }
        return household;
    }

    @Override
    public Household updateHousehold(Long id, Household householdDetails) {
        Household household = getHouseholdById(id, true);
        household.setEircode(householdDetails.getEircode());
        household.setNumberOfOccupants(householdDetails.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(householdDetails.getMaxNumberOfOccupants());
        household.setOwnerOccupied(householdDetails.isOwnerOccupied());
        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdById(Long id) {
        if (!householdRepository.existsById(id)) {
            throw new HouseholdNotFoundException("Household not found");
        }
        householdRepository.deleteById(id);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findByPetsIsNull();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public long countEmptyHouses() {
        return householdRepository.findAll()
                .stream()
                .filter(household -> household.getNumberOfOccupants() == 0)
                .count();
    }

    @Override
    public long countFullHouses() {
        return householdRepository.findAll()
                .stream()
                .filter(household -> household.getNumberOfOccupants() == household.getMaxNumberOfOccupants())
                .count();
    }
}
