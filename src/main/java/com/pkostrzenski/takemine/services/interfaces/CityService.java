package com.pkostrzenski.takemine.services.interfaces;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    int NAME_ALREADY_TAKEN = 0;
    int INCORRECT_LAT_LNG = 1;
    int ALREADY_SET_ID = 2;
    int ID_NOT_FOUND = 3;

    List<City> getAllCities();
    Optional<City> getCityById(Integer cityId);
    City addCity(City city) throws ServiceException;

}
