package com.pkostrzenski.takemine.services.interfaces;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.Category;
import com.pkostrzenski.takemine.models.ItemType;
import com.pkostrzenski.takemine.models.Product;

import java.util.List;

public interface ProductService {

    List<Category> getAllCategories();
    List<ItemType> getItemTypesByCategoryId(int categoryId);

    Product getProductById(int id);
    List<Product> getAllProductsByCityId(int cityId);

    Product addProduct(Product product, String username) throws ServiceException;
}
