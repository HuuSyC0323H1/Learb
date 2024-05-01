package com.core.repositories;

import com.core.model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Huu Sy
 * @Date: 30/04/2024 4:17 CH
 */

@Repository
public interface TrademarkRepository extends JpaRepository<Brands, Long> {
    Brands findBrandsById(Long id);
}
