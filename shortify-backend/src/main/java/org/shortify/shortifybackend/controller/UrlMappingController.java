package org.shortify.shortifybackend.controller;


import lombok.RequiredArgsConstructor;
import org.shortify.shortifybackend.dto.ClickEventDto;
import org.shortify.shortifybackend.dto.UrlMappingDto;
import org.shortify.shortifybackend.model.UrlMapping;
import org.shortify.shortifybackend.model.User;
import org.shortify.shortifybackend.service.AuthServiceImpl;
import org.shortify.shortifybackend.service.UrlMappingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlMappingController {

    private final UrlMappingService urlMappingService;
    private final AuthServiceImpl authService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlMappingDto> shortenUrl(@RequestBody Map<String, String> request, Principal principal) {
        String originalUrl = request.get("originalUrl");
        User user = authService.findByUsername(principal.getName());
        UrlMappingDto urlMappingDto = urlMappingService.shortenUrl(originalUrl, user);
        return ResponseEntity.ok(urlMappingDto);
    }

    @GetMapping("/myurls")
    public ResponseEntity<List<UrlMappingDto>> getUserUrls(Principal principal) {
        List<UrlMappingDto> response = urlMappingService.getUrlByUser(principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/analytics/{shorturl}")
    public ResponseEntity<List<ClickEventDto>> getUrlAnalytics(
            @PathVariable String shorturl,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate
    ) {
        List<ClickEventDto> response =
                urlMappingService.getClickEventsByDate(shorturl, startDate, endDate);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/totalClicks")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(
            Principal principal,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) {
        Map<LocalDate, Long> response =
                urlMappingService.getTotalClicksByUserAndDate(principal, startDate, endDate);

        return ResponseEntity.ok(response);
    }


}
