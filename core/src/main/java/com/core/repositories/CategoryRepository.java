package com.core.repositories;

import com.core.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c.* FROM category c WHERE c.name = :name OR :name = ''", nativeQuery = true)
    List<Category> getListCategory(@Param("name") String name);

    Category getCategoryById(Long categoryId);
}
