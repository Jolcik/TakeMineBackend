package com.pkostrzenski.takemine.services.interfaces;

import com.pkostrzenski.takemine.models.Picture;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();

    Picture store(MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);

}
