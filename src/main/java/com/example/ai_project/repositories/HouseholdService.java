package com.example.myapp.services;

import com.example.myapp.entities.Household;

import java.util.List;

public interface HouseholdService {
    Household createHousehold(Household household);
    List<Household> getAllHouseholds();
    Household getHouseholdById(Long id, boolean includePets);
    Household updateHousehold(Long id, Household householdDetails);
    void deleteHouseholdById(Long id);
    List<Household> findHouseholdsWithNoPets();
    List<Household> findOwnerOccupiedHouseholds();
    long countEmptyHouses();
    long countFullHouses();
}
