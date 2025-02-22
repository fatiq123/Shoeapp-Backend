package com.fatiq.shoeapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
}

// Notes: This enables auditing for @CreatedDate and @LastModifiedDate.