package com.core.repositories;

import com.core.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Huu Sy
 * @Date: 30/04/2024 4:15 CH
 */

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Condition findConditionById(Long id);
}
