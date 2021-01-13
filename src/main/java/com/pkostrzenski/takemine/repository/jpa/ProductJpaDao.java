package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Category;
import com.pkostrzenski.takemine.models.ItemType;
import com.pkostrzenski.takemine.models.Product;
import com.pkostrzenski.takemine.repository.interfaces.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ProductJPA")
public class ProductJpaDao implements ProductDao {

    @Autowired
    ProductJpaRepository productJpaRepository;

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public List<ItemType> getItemTypesByCategoryId(int categoryId) {
        Category category = categoryJpaRepository.findById(categoryId).get();
        return category.getItems();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductsByCityId(int cityId) {
        return productJpaRepository.findByCityId(cityId);
    }

    @Override
    public Product save(Product product) {
        return productJpaRepository.saveAndFlush(product);
    }
}
