package org.example.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide ou null");
        }

        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "films",
                            "resource_type", "image",
                            "transformation", ObjectUtils.asMap(
                                    "width", 300,
                                    "height", 400,
                                    "crop", "fill"
                            )
                    )
            );

            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'upload de l'image : " + e.getMessage(), e);
        }
    }

    public void deleteImage(String imageUrl) {
        try {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                // Extraire l'ID public de l'URL Cloudinary
                String publicId = extractPublicIdFromUrl(imageUrl);
                if (publicId != null) {
                    cloudinary.uploader().destroy("films/" + publicId, ObjectUtils.emptyMap());
                }
            }
        } catch (Exception e) {
            // Log l'erreur mais ne pas faire échouer l'opération
            System.err.println("Erreur lors de la suppression de l'image : " + e.getMessage());
        }
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        try {
            // Exemple d'URL : https://res.cloudinary.com/yourcloud/image/upload/v1234567890/films/abc123.jpg
            String[] parts = imageUrl.split("/");
            String fileName = parts[parts.length - 1];
            return fileName.substring(0, fileName.lastIndexOf('.'));
        } catch (Exception e) {
            return null;
        }
    }
}