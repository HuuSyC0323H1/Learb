package com.core.infrastructure.constant;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 24/04/2024 10:34 CH
 */

@Data
@Entity
@Table(name = "message_template")
public class MessageTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String message;
    private Long langCodeId;
}
