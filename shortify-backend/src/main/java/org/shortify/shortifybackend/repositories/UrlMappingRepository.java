package org.shortify.shortifybackend.repositories;

import org.shortify.shortifybackend.model.UrlMapping;
import org.shortify.shortifybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, UUID> {
    List<UrlMapping> findByUser(User user);
    UrlMapping findByShortUrl(String shorturl);
}