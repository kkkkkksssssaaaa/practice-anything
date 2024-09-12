package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@EnableJpaAuditing
@Configuration
class AuditConfig {
    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware<String> {
            Optional.ofNullable(SecurityContextHolder.getContext())
                .map { it.authentication }
                .map { it.name }
        }
    }
}