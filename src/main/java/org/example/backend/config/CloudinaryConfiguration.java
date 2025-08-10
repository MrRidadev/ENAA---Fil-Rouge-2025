package org.example.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfiguration {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        // AJOUT: Vérification des credentials au démarrage
        System.out.println("Configuration Cloudinary:");
        System.out.println("Cloud Name: " + cloudName);
        System.out.println("API Key: " + apiKey);
        System.out.println("API Secret: " + (apiSecret != null ? "***" + apiSecret.substring(apiSecret.length()-4) : "null"));

        if (cloudName == null || apiKey == null || apiSecret == null) {
            throw new RuntimeException("Configuration Cloudinary incomplète! Vérifiez application.properties");
        }

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));

        // TEST de connexion
        try {
            Map result = cloudinary.api().resource("sample", ObjectUtils.emptyMap());
            System.out.println("Connexion Cloudinary réussie !");
        } catch (Exception e) {
            System.err.println("Erreur connexion Cloudinary: " + e.getMessage());
        }


        return cloudinary;
    }
}
