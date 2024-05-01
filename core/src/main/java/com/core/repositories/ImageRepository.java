package com.core.repositories;

import com.core.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "SELECT * FROM images i WHERE i.product_id = :id ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Image findRandomImageByProductId(Long id);
}
