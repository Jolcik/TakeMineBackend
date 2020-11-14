package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.City;
import com.pkostrzenski.takemine.repository.interfaces.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("CityJPA")
public class CityJpaDao implements CityDao {

    @Autowired
    CityJpaRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(int id) {
        return cityRepository.findById(id);
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public City save(City city) {
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public boolean existsByName(String name) {
        return cityRepository.existsByName(name);
    }
}
