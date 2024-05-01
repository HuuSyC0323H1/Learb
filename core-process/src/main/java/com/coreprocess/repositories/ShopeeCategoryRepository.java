package com.coreprocess.repositories;

import com.coreprocess.model.ShopeeCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Huu Sy
 * @Date: 01/05/2024 8:28 SA
 */

@Repository
public interface ShopeeCategoryRepository extends MongoRepository<ShopeeCategory, Long> {

}
