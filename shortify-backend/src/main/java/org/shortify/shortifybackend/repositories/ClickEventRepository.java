package org.shortify.shortifybackend.repositories;

import org.shortify.shortifybackend.model.ClickEvent;
import org.shortify.shortifybackend.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ClickEventRepository extends JpaRepository<ClickEvent, UUID> {
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping urlMapping , LocalDateTime start , LocalDateTime end);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMapping , LocalDate start , LocalDate end);
}