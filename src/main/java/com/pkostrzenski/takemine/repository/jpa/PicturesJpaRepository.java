package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicturesJpaRepository extends JpaRepository<Picture, Integer> {
}
