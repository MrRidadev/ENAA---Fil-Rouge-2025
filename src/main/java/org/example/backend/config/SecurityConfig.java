package org.example.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Routes publiques (pas besoin d'authentification)
                        .requestMatchers("/auth/**","/reservation/count").permitAll()
                        .requestMatchers("/film/getAllFilm").permitAll()
                        .requestMatchers("/seance/getAllSeance").permitAll()
                        .requestMatchers("/salle/getAll").permitAll()

                        // Routes protégées pour les admins
                        .requestMatchers("/film/addFilm", "/film/updateWithImage/**", "/film/delete/**").hasRole("ADMIN")
                        .requestMatchers("/seance/addSeance", "/seance/updateSeance/**", "/seance/deleteSeance/**").hasRole("ADMIN")
                        .requestMatchers("/salle/addSalle","/salle/delete/**").hasRole("ADMIN")
                        .requestMatchers("/auth/all/**", "/auth/update/**").hasRole("ADMIN")

                        // Routes protégées pour les clients
                        .requestMatchers("/reservation/**").hasAnyRole("CLIENT", "ADMIN")

                        // Toute autre requête nécessite une authentification
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //  Config CORS appliquée à Spring Security
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}