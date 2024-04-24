package com.core.repositories;

import com.core.model.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: Huu Sy
 * @Date: 24/04/2024 11:01 CH
 */

@Repository
public interface MsTemplateRepository extends JpaRepository<MessageTemplate, Long> {

    @Query(value = "select ms.message from message_template ms join languages ls on ms.lang_code_id = ls.id " +
            "where ms.code = :code and ls.code = :langCode", nativeQuery = true)
    String getByCode(String code, String langCode);
}
