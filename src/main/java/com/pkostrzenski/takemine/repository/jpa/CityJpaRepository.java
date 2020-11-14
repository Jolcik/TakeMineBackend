package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Integer> {
    boolean existsByName(String name);
    Optional<City> findByName(String name);
}
