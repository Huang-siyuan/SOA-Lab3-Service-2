package com.example.secondservice.service;


import com.example.secondservice.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class NavigatorService {

    private final RequestService requestService;
    private final RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:9090/navigator/routes/";

    public Route findLongestOrShortestRoute(int idFrom, int idTo, int shortest) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + idFrom + "/" + idTo + "/" + shortest;
        return restTemplate.postForObject(uri, null, Route.class);
    }


    public Route createRouteBetweenLocations(int idFrom, int idTo) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + idFrom + "/" + idTo;
        return restTemplate.postForObject(uri, null, Route.class);
    }
}
