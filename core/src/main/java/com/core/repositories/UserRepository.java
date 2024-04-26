package com.core.repositories;

import com.core.model.Languages;
import com.core.model.User;
import com.core.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 1:29 SA
 */

@Repository
public interface UserRepository extends JpaRepository<User, Languages> {
    Optional<User> findByUserName(String userName);
    User findByRole(Role role);
    User getUserByUserName(String userName);
}
