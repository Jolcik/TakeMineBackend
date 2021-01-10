package com.pkostrzenski.takemine.services;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.*;
import com.pkostrzenski.takemine.repository.interfaces.ProductDao;
import com.pkostrzenski.takemine.repository.interfaces.UserDao;
import com.pkostrzenski.takemine.repository.jpa.LocationJpaDao;
import com.pkostrzenski.takemine.repository.jpa.LocationJpaRepository;
import com.pkostrzenski.takemine.repository.jpa.PicturesJpaDao;
import com.pkostrzenski.takemine.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.pkostrzenski.takemine.services.interfaces.UserService.USER_NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    PicturesJpaDao picturesJpaDao;

    @Autowired
    LocationJpaDao locationJpaDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<Category> getAllCategories() {
        return productDao.getAllCategories();
    }

    @Override
    public List<ItemType> getItemTypesByCategoryId(int categoryId) {
        return productDao.getItemTypesByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getAllProductsByCityId(int cityId) {
        return productDao.getAllProductsByCityId(cityId);
    }

    @Override
    public Product addProduct(Product product, String username) throws ServiceException {
        product.getLocations().forEach(location -> location.setProduct(product));
        product.getPictures().forEach(picture -> picture.setProduct(product));
        productDao.save(product);

        Optional<User> owner = userDao.findByUsername(username);
        if (!owner.isPresent())
            throw new ServiceException("Incorrect user!", USER_NOT_FOUND);

        product.setOwner(owner.get());

        product.getPictures().forEach(picture -> picturesJpaDao.save(picture));
        product.getLocations().forEach(location -> locationJpaDao.save(location));
        return product;
    }
}
