package com.pkostrzenski.takemine.repository.interfaces;

import com.pkostrzenski.takemine.models.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    List<City> findAll();
    Optional<City> findById(int id);
    Optional<City> findByName(String name);
    City save(City city);
    boolean existsByName(String name);
}
