package com.example.secondservice.controller;

import com.example.secondservice.dto.FilterDto;
import com.example.secondservice.dto.MessageDto;
import com.example.secondservice.dto.PaginationDto;
import com.example.secondservice.dto.RouteDto;
import com.example.secondservice.entity.Route;
import com.example.secondservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://se.ifmo.ru'"})
public class RouteController {

    private final RouteService routeService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/{id}")
    public ResponseEntity findRouteById(@PathVariable("id") long id) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
         Route route = routeService.getRouteById(id);
         if(route == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
         return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    @PostMapping("/filter")
    public ResponseEntity getRoutes(@RequestBody PaginationDto paginationDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        if(paginationDto.getPageNumber() == null || paginationDto.getPageSize() == null ||
                paginationDto.getPageNumber() <= 0 || paginationDto.getPageSize() <= 0) {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body("Invalid parameters");
        }
        List routes = routeService.getRoutes(paginationDto);
        if(routes == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameters");
        }
        if(routes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Routes not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(routes);
    }

    @PostMapping("/count")
    public ResponseEntity getRoutesCount(@RequestBody FilterDto filterDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        Long routesCount = routeService.getRoutesCount(filterDto);
        if(routesCount == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Routes not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(routesCount);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteRouteById(@PathVariable("id") long id) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Integer status = routeService.deleteRouteById(id);
        if(status == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Route Deleted");
    }

    @PostMapping("/distances/sum")
    public ResponseEntity getRouteSumDistance() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        Long sum = routeService.getDistancesSum();
        if(sum == null) {
            return ResponseEntity.status(HttpStatus.OK).body("There are no distances");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sum);
    }


    @PostMapping("/distances/less/{value}")
    public ResponseEntity getLessDistances(@PathVariable("value") int value) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        Long distanceNumber = routeService.getDistanceNumber(value);
        if(distanceNumber == null) {
            return ResponseEntity.status(HttpStatus.OK).body("There are no distances");
        }
        return ResponseEntity.status(HttpStatus.OK).body(distanceNumber);
    }

    @PostMapping("/distances/unique")
    public ResponseEntity getUniqueDistances(@RequestBody PaginationDto paginationDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        if(paginationDto.getPageNumber() == null || paginationDto.getPageSize() == null ||
                paginationDto.getPageNumber() <= 0 || paginationDto.getPageSize() <= 0) {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body("Invalid parameters");
        }
        List uniqueDistances = routeService.getUniqueDistances(paginationDto.getPageNumber(), paginationDto.getPageSize());
        if(uniqueDistances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distances not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(uniqueDistances);
    }


    @PostMapping("/distances/unique/count")
    public ResponseEntity getUniqueDistancesCount() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        Integer uniqueDistancesCount = routeService.getUniqueDistancesCount();
        if(uniqueDistancesCount == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distances not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(uniqueDistancesCount);
    }

    @PostMapping(value = "/", consumes = {"application/json"})
    public ResponseEntity createRoute(@RequestBody RouteDto routeDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        MessageDto messageDto = routeService.createRoute(routeDto);
        log.info("Message: " + messageDto.getMessage());
        if(messageDto.getRoute() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageDto.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDto.getRoute());
    }


    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public ResponseEntity updateRoute(@PathVariable("id") long id, @RequestBody RouteDto routeDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        MessageDto messageDto = routeService.updateRoute(id, routeDto);
        if(messageDto.getRoute() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageDto.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageDto.getRoute());
    }


    @GetMapping("/test")
    public String getMessage() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        return routeService.getTest();
    }

    @GetMapping("/test-load-balancer")
    public String testLoadBalancer() {
        return "Hello from port: " + serverPort;
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleBaseExceptions() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }


}

