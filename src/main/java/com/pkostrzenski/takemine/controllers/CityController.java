package com.pkostrzenski.takemine.controllers;

import com.pkostrzenski.takemine.controllers.request_models.ErrorResponse;
import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.City;
import com.pkostrzenski.takemine.services.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CityController {

    @Autowired
    CityService cityService;

    // we do not check authorization here, because security config does it for us
    @GetMapping("api/cities")
    public ResponseEntity<?> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

    // only for admins, authentication set in security config
    @PostMapping("api/cities")
    public ResponseEntity<?> addCity(@Valid @RequestBody City city){
        try {
            return ResponseEntity.ok(cityService.addCity(city) );
        } catch (ServiceException e) {
            return ErrorResponse.create(HttpStatus.CONFLICT, e.getMessage(), e.getErrorCode());
        }
    }
}
