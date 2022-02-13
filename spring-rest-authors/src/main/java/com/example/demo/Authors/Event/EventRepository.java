package com.example.demo.Authors.Event;

import com.example.demo.Authors.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public EventRepository(@Value("${demo.books.url}") String baseUrl){
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Long id){
        restTemplate.delete("/authors/{Id}",id);
    }

    public void create(Author author){
        restTemplate.postForLocation("/authors",author);
    }
}
