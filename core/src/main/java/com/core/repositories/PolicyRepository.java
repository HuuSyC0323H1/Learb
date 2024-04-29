package com.core.repositories;

import com.core.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Huu Sy
 * @Date: 29/04/2024 7:41 CH
 */

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Policy findPoliciesById(Long id);
}
