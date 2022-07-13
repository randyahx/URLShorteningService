package com.randyahx.backend.controller;

import com.randyahx.backend.entity.Url;
import com.randyahx.backend.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/get/{id}")
    public String getUrl(@PathVariable String id) {
        Url url = urlService.getUrlByBaseUrl(id);
        return url.getShortenedUrl();
    }

    @PostMapping("/create")
    public List<String> createUrl(@RequestParam String base_url) {
        Url created_url = urlService.create(base_url);
        return List.of(created_url.getBaseUrl(), created_url.getShortenedUrl());
    }

    @GetMapping("/{id}")
    public RedirectView redirectShortenedUrl(@PathVariable String id) {
        // Configure to return {root_url}/shortenedUrl/{id} on frontend
        Url url = urlService.getUrlByShortenedUrl(id);
        String base_url = url.getBaseUrl();

        // http://localhost:8081/585ceb83
        // Redirect with backend instead of frontend since the user might access the link directly
        return new RedirectView(base_url);
    }
}
