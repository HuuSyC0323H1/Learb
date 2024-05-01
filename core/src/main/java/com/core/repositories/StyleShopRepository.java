package com.core.repositories;

import com.core.model.StyleShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Huu Sy
 * @Date: 30/04/2024 4:19 CH
 */

@Repository
public interface StyleShopRepository extends JpaRepository<StyleShop, Long> {
    StyleShop findStyleShopById(Long id);
}
