package com.pkostrzenski.takemine.services.interfaces;

import com.pkostrzenski.takemine.models.Product;

public interface PushService {
    boolean sendEventToUsersByProduct(Product product);
}
