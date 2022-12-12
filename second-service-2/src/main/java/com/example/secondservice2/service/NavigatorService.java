package com.example.secondservice2.service;

import com.example.secondservice2.entity.Route;
import lombok.RequiredArgsConstructor;
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

    public Route findLongestOrShortestRoute(int idFrom, int idTo, int shortest) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "http://localhost:9090/navigator/routes/" + idFrom + "/" + idTo + "/" + shortest;
        return restTemplate.postForObject(uri, null, Route.class);
    }


    public Route createRouteBetweenLocations(int idFrom, int idTo) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "http://localhost:9090/navigator/routes/" + idFrom + "/" + idTo;
        return restTemplate.postForObject(uri, null, Route.class);
    }
}
