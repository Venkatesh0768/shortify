package org.shortify.shortifybackend.controller;


import lombok.RequiredArgsConstructor;
import org.shortify.shortifybackend.dto.UrlMappingDto;
import org.shortify.shortifybackend.model.User;
import org.shortify.shortifybackend.service.AuthServiceImpl;
import org.shortify.shortifybackend.service.UrlMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlMappingController {

    private final UrlMappingService urlMappingService;
    private final AuthServiceImpl authService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlMappingDto> shortenUrl(@RequestBody Map<String , String> request , Principal principal){
        String originalUrl = request.get("originalUrl");
        User user  = authService.findByUsername(principal.getName());
        UrlMappingDto urlMappingDto = urlMappingService.shortenUrl(originalUrl, user);
        return ResponseEntity.ok(urlMappingDto);
    }
}
