package com.core.repositories;

import com.core.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query(value = "select p.* from products p ",nativeQuery = true)
    Page<Products> getListProduct(String name, String langCode, Pageable pageable);
}
