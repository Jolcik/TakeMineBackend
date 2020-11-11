package com.pkostrzenski.takemine.services;

import com.pkostrzenski.takemine.models.Category;
import com.pkostrzenski.takemine.models.ItemType;
import com.pkostrzenski.takemine.models.Product;
import com.pkostrzenski.takemine.repository.interfaces.ProductDao;
import com.pkostrzenski.takemine.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

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
    public Product addProduct(Product product) {
        return productDao.save(product);
    }
}
