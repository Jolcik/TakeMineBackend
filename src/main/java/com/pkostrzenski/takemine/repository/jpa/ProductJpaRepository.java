package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    @Query(
            value = "SELECT p.* " +
                    "FROM takemine.product p " +
                    "WHERE p.city_id = :cityId",
            nativeQuery = true
    )
    List<Product> findByCityId(@Param("cityId") int cityId);
}
