package org.shortify.shortifybackend.service;


import lombok.RequiredArgsConstructor;
import org.shortify.shortifybackend.dto.UrlMappingDto;
import org.shortify.shortifybackend.model.UrlMapping;
import org.shortify.shortifybackend.model.User;
import org.shortify.shortifybackend.repositories.UrlMappingRepository;
import org.shortify.shortifybackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final UserRepository userRepository;

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

    /**
     * Generates random alphanumeric short URL of fixed length
     */
    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTQUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }

    /**
     * Gets user URLs; maps to DTOs; returns as list
     */
    public List<UrlMappingDto> getUrlByUser(Principal principal) {
        User user =  userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Username is not Found " + principal.getName()));

        return urlMappingRepository.findByUser(user).stream().map(this::mappingDto).collect(Collectors.toList());
    }


}
