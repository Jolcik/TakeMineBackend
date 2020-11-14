package com.pkostrzenski.takemine.repository.jpa;


import com.pkostrzenski.takemine.models.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeJpaRepository extends JpaRepository<ItemType, Integer> {
}
