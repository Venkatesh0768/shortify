package org.shortify.shortifybackend.service;


import lombok.RequiredArgsConstructor;
import org.shortify.shortifybackend.dto.UrlMappingDto;
import org.shortify.shortifybackend.model.UrlMapping;
import org.shortify.shortifybackend.model.User;
import org.shortify.shortifybackend.repositories.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlMappingDto shortenUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = UrlMapping.builder()
                .originalUrl(originalUrl)
                .user(user)
                .shortUrl(shortUrl)
                .build();

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return mappingDto(savedUrlMapping);
    }

    public UrlMappingDto mappingDto(UrlMapping urlMapping) {
        return UrlMappingDto.builder()
                .originalUrl(urlMapping.getOriginalUrl())
                .shortUrl(urlMapping.getShortUrl())
                .clickCount(urlMapping.getClickCount())
                .createdAt(urlMapping.getCreatedAt())
                .updatedAt(urlMapping.getUpdatedAt())
                .build();
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTQUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }
}
