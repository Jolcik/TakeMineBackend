package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PicturesJPA")
public class PicturesJpaDao {

    @Autowired
    PicturesJpaRepository picturesJpaRepository;

    public Picture save(Picture picture) {
        return picturesJpaRepository.save(picture);
    }
}
