package com.example.prueba_tecnica.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 1. Desactiva CSRF para permitir POST desde Postman
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 2. Modo API (sin estado)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // 3. Permite libremente tus endpoints de login
                .requestMatchers("/images/**", "/css/**", "/js/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable()) // 4. Desactiva el formulario para evitar redirecciones infinitas
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    // ================= PASSWORD ENCODER =================
    // He cambiado BCrypt a NoOp momentáneamente para que coincida con tu "12345" de la DB
    @Bean
public PasswordEncoder passwordEncoder() {
    // Usamos el nombre completo de la clase para que no se borre el import
    return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
}

    // ================= AUTH MANAGER =================
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
