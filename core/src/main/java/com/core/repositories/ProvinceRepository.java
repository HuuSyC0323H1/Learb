package com.core.repositories;

import com.core.api.view.ProvinceView;
import com.core.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    @Query(value = "select new com.core.api.view.ProvinceView(pr.id, pr.code, pr.name) from Province pr where pr.id = :id")
    ProvinceView findProvinceById(Long id);
}
