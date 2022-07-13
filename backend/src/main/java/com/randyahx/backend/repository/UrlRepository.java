package com.randyahx.backend.repository;

import com.randyahx.backend.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
    Optional<Url> getUrlByBaseUrl(String url);
    Optional<Url> getUrlByShortenedUrl(String url);
}
