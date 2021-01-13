package com.pkostrzenski.takemine.services.interfaces;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.Category;
import com.pkostrzenski.takemine.models.ItemType;
import com.pkostrzenski.takemine.models.Product;
import com.pkostrzenski.takemine.models.User;

import java.util.List;

public interface ProductService {

    List<Category> getAllCategories();
    List<ItemType> getItemTypesByCategoryId(int categoryId);

    Product getProductById(int id) throws ServiceException;
    List<Product> getAllProductsByCityId(int cityId);

    Product addProduct(Product product, String username) throws ServiceException;
    User buyProduct(Integer productId, String username) throws ServiceException;
}
