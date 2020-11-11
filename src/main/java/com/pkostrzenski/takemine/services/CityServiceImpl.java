package com.pkostrzenski.takemine.services;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.City;
import com.pkostrzenski.takemine.repository.interfaces.CityDao;
import com.pkostrzenski.takemine.services.interfaces.CityService;
import com.pkostrzenski.takemine.utils.LatLngValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    CityDao cityDao;

    @Autowired
    public CityServiceImpl(@Qualifier("CityJPA") CityDao cityDao){
        this.cityDao = cityDao;
    }

    @Override
    public List<City> getAllCities() {
        return cityDao.findAll();
    }

    @Override
    public Optional<City> getCityById(Integer cityId) {
        return cityDao.findById(cityId);
    }

    @Override
    public City addCity(City city) throws ServiceException {
        if(city.getId() != 0) // added city should not have any ID set before saving in database
            throw new ServiceException("ID can not be set manually", ALREADY_SET_ID);

        if(cityDao.existsByName(city.getName()))
            throw new ServiceException("City name is already taken", NAME_ALREADY_TAKEN);

        validateLatLng(city.getCenterLat(), city.getCenterLng());

        return cityDao.save(city);
    }

    private void validateLatLng(double lat, double lng) throws ServiceException{
        if(!LatLngValidator.validateLatLng(lat, lng))
            throw new ServiceException("Incorrect latitude or longitude value", INCORRECT_LAT_LNG);
    }
}
