package com.example.secondservice2.controller;

import com.example.secondservice2.entity.Route;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SOA_Service_2
 * @author: Siyuan
 * @create: 2022-12-01 00:10
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
    private final RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable("id") Long id) {
        log.info("Trying to get route {}.", id);
        return restTemplate.getForObject("https://localhost:8086/routes/" + id, Route.class);
    }
}
