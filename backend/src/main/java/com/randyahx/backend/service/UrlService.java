package com.randyahx.backend.service;

import com.google.common.hash.Hashing;
import com.randyahx.backend.entity.Url;
import com.randyahx.backend.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service("urlService")
@RequiredArgsConstructor
public class UrlService {

    // Autowire UrlRepository with @RequiredArgsConstructor
    private final UrlRepository urlRepository;

    public Url getUrlByBaseUrl(String base_url) {
        Optional<Url> url = urlRepository.getUrlByBaseUrl(base_url);
        return url.orElseThrow(() -> new IllegalArgumentException("URL is not found."));
    }

    public Url getUrlByShortenedUrl(String shortened_url) {
        Optional<Url> url = urlRepository.getUrlByShortenedUrl(shortened_url);
        return url.orElseThrow(() -> new IllegalArgumentException("URL is not found."));
    }

    public void save(Url url) {
        urlRepository.save(url);
    }

    public Url create(String base_url) {

        String shortened_url = "";

        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});

        if (urlValidator.isValid(base_url)) {
            shortened_url = Hashing.murmur3_32().hashString(base_url, StandardCharsets.UTF_8).toString();
        } else {
            throw new IllegalArgumentException("ERROR: URL provided is invalid. Please ensure that the URL is properly formatted.");
        }

        Url created_url = new Url(shortened_url, base_url);
        save(created_url);

        return created_url;
    }
}
