package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.configuration

import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.properties.NotAuthenticatedEndpoints
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.auth.service.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration(
    private val authenticationFilter: AuthenticationFilter,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors {}
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(
                    NotAuthenticatedEndpoints.LOGIN,
                    NotAuthenticatedEndpoints.REGISTRATION,
                    NotAuthenticatedEndpoints.TOKEN_REFRESH
                ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}