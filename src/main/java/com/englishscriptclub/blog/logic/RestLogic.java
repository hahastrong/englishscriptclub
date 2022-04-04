package com.englishscriptclub.blog.logic;

import com.englishscriptclub.blog.entity.ESCAudioUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.jws.Oneway;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestLogic {
    private RestTemplate rest;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    public void getAudiosById(int id) {
        String ii = String.valueOf(id);
        Object obj = rest.getForObject("http://localhost:8080/api/audio/{id}", Object.class, ii);
        System.out.printf("get by id: %s\n", obj);
    }

    public void getAudioByIdByMap(int id) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", String.valueOf(id));
        Object obj = rest.getForObject("http://localhost:8080/api/audio/{id}", Object.class, urlVariables);
        System.out.printf("get by urlMap: %s\n", obj);
    }

    public void getAudioByIdByUrl(int id) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", String.valueOf(id));
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/audio/{id}")
                .build(urlVariables);
        Object obj = rest.getForObject(url, Object.class);
        System.out.printf("get by URI: %s\n", obj);
    }

    public void getAudioByIdUsingEntity(int id) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", String.valueOf(id));
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/audio/{id}")
                .build(urlVariables);
        ResponseEntity<Object> obj = rest.getForEntity(url, Object.class);
        System.out.printf("Fetched Time: %s\n", obj.getHeaders().getDate());
    }

    public void postAudio(ESCAudioUrl audio) {
        rest.postForObject("http://localhost:8080/api/audio", audio, Object.class);

    }

}
