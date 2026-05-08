package com.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookStoreApiController {

    @GetMapping("/api/it-books")
    public String getBooks() {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(
                "https://holy-bible-api.com/bibles",
                String.class
        );
    }
}