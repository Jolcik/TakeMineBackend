package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {
}
