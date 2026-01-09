package org.shortify.shortifybackend.controller;


import lombok.RequiredArgsConstructor;
import org.shortify.shortifybackend.dto.UrlMappingDto;
import org.shortify.shortifybackend.model.User;
import org.shortify.shortifybackend.service.AuthServiceImpl;
import org.shortify.shortifybackend.service.UrlMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
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

    @GetMapping("/myurls")
    public ResponseEntity<List<UrlMappingDto>>  getUserUrls(Principal principal){
        List<UrlMappingDto> response = urlMappingService.getUrlByUser(principal);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
