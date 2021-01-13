package com.pkostrzenski.takemine.services;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.*;
import com.pkostrzenski.takemine.repository.interfaces.ProductDao;
import com.pkostrzenski.takemine.repository.interfaces.UserDao;
import com.pkostrzenski.takemine.repository.jpa.LocationJpaDao;
import com.pkostrzenski.takemine.repository.jpa.PicturesJpaDao;
import com.pkostrzenski.takemine.services.interfaces.ProductService;
import com.pkostrzenski.takemine.services.interfaces.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    PushService pushService;

    @Override
    public List<Category> getAllCategories() {
        return productDao.getAllCategories();
    }

    @Override
    public List<ItemType> getItemTypesByCategoryId(int categoryId) {
        return productDao.getItemTypesByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(int id) throws ServiceException {
        Optional<Product> product = productDao.getProductById(id);
        if (!product.isPresent())
            throw new ServiceException("Did not find such resource!", USER_NOT_FOUND);

        return product.get();
    }

    @Override
    public List<Product> getAllProductsByCityId(int cityId) {
        return productDao
                .getAllProductsByCityId(cityId)
                .stream()
                .filter(product -> !product.isSold())
                .collect(Collectors.toList());
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
        pushService.sendEventToUsersByProduct(product);
        return product;
    }

    @Override
    public User buyProduct(Integer productId, String username) throws ServiceException {
        Optional<Product> product = productDao.getProductById(productId);
        Optional<User> user = userDao.findByUsername(username);

        if (!product.isPresent() || !user.isPresent())
            throw new ServiceException("Did not find such resource!", USER_NOT_FOUND);

        if (product.get().getBuyer() != null)
            return null;

        product.get().setBuyer(user.get());
        product.get().setSold(true);
        productDao.save(product.get());

        // check second time to avoid conflicts
        Product productAgain = productDao.getProductById(productId).get();
        if (productAgain.getBuyer().getId() == user.get().getId())
            return productAgain.getOwner();

        return null;
    }
}
