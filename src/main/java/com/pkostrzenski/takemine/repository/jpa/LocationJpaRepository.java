package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationJpaRepository extends JpaRepository<Location, Integer> {
}
