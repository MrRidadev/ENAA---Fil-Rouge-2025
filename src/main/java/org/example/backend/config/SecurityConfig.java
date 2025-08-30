package org.example.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Routes publiques (pas besoin d'authentification)
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/film/getAllFilm").permitAll()
                        .requestMatchers("/seance/getAllSeance").permitAll()
                        .requestMatchers("/salle/getAll").permitAll()

                        // Routes protégées pour les admins
                        .requestMatchers("/film/addFilm", "/film/updateWithImage/**", "/film/delete/**").hasRole("ADMIN")
                        .requestMatchers("/seance/addSeance", "/seance/updateSeance/**", "/seance/deleteSeance/**").hasRole("ADMIN")
                        .requestMatchers("/salle/addSalle").hasRole("ADMIN")
                        .requestMatchers("/auth/all/**", "/auth/update/**").hasRole("ADMIN")

                        // Routes protégées pour les clients
                        .requestMatchers("/reservation/**").hasAnyRole("CLIENT", "ADMIN")

                        // Toute autre requête nécessite une authentification
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}