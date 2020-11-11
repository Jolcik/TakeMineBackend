package com.pkostrzenski.takemine.repository.interfaces;

import com.pkostrzenski.takemine.models.Category;
import com.pkostrzenski.takemine.models.ItemType;
import com.pkostrzenski.takemine.models.Product;

import java.util.List;

public interface ProductDao {

    List<Category> getAllCategories();
    List<ItemType> getItemTypesByCategoryId(int categoryId);

    Product getProductById(int id);
    List<Product> getAllProductsByCityId(int cityId);

    Product save(Product product);
}
