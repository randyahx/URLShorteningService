package com.randyahx.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Url {

    @Id
    private String shortenedUrl;

    @Column(length = 1000)
    private String baseUrl;

//  --------------------------------------------------------------------------------------
//    Can be replaced with @AllArgsConstructor @NoArgsConstructor @Getter @Setter
//    Doing it the "traditional" way for this assessment
//  --------------------------------------------------------------------------------------

    public Url() {
    }

    public Url(String shortenedUrl, String baseUrl) {
        this.shortenedUrl = shortenedUrl;
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }
}
