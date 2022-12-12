package com.example.secondservice2.service;

import com.example.secondservice2.dto.FilterDto;
import com.example.secondservice2.dto.MessageDto;
import com.example.secondservice2.dto.PaginationDto;
import com.example.secondservice2.dto.RouteDto;
import com.example.secondservice2.entity.Route;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {

    private final RequestService requestService;
    private final RestTemplate restTemplate;

    // Port 9090 is for Haproxy. It will implement load balancer
    private final String baseUrl = "http://localhost:9090/routes/";

    public Route getRouteById(long id) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + id;
        log.info("Trying to get route {}.", id);
        return restTemplate.getForObject(uri, Route.class);
    }



    public Integer deleteRouteById(long id) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + id;
        if(getRouteById(id) == null) {
            return -1;
        }
        log.info("Trying to delete route {}.", id);
        return restTemplate.exchange(uri, HttpMethod.DELETE, null, Integer.class).getStatusCodeValue();
    }


    public Long getDistancesSum() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "distances/sum";
        return restTemplate.postForObject(uri, null, Long.class);
    }

    public Long getDistanceNumber(int value) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "distances/less/" + value;
        return restTemplate.postForObject(uri, null, Long.class);
    }


    public List getUniqueDistances(int pageNumber, int pageSize) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "distances/unique?pageSize=" + pageSize + "&pageNumber=" + pageNumber;
        return restTemplate.postForObject(uri, null, List.class);
    }

    public Integer getUniqueDistancesCount() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "distances/unique/count";
        return restTemplate.postForObject(uri, null, Integer.class);
    }

    public MessageDto createRoute(RouteDto routeDto) throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl;
        log.info("Trying to create route {}.", routeDto.toString());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(routeDto);
        log.info("JSON: {}", json);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri, HttpMethod.POST, requestEntity, MessageDto.class);
//        return restTemplate.postForObject(uri, requestEntity, MessageDto.class);
    }


    public List getRoutes(PaginationDto paginationDto) throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "filter";
        for (Map.Entry<String, String> entry : paginationDto.getFilters().entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(paginationDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri,HttpMethod.POST,requestEntity,List.class);
//        return restTemplate.postForObject(uri, requestEntity, List.class);
    }


    public Long getRoutesCount(FilterDto filterDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "count";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(filterDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri,HttpMethod.POST,requestEntity,Long.class);
//        return restTemplate.postForObject(uri, requestEntity, Long.class);
    }


    public String getTest() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + "test";
        return restTemplate.getForObject(uri, String.class);
    }

    // I don't know how to rewrite this method :(
    public MessageDto updateRoute(long id, RouteDto routeDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = baseUrl + id;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(routeDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri, HttpMethod.PUT, requestEntity, MessageDto.class);
    }


}
