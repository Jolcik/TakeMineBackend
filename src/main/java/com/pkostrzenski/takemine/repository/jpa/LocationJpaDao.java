package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("LocationJPA")
public class LocationJpaDao {

    @Autowired
    LocationJpaRepository locationJpaRepository;

    public Location save(Location location) {
        return locationJpaRepository.save(location);
    }
}
