package com.example.secondservice;

//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SOA_Service_2
 * @author: Siyuan
 * @create: 2022-12-01 00:11
 **/

@Configuration
public class ApplicationContextConfig {
    /**
     * @return RestTemplate
     * @description: RestTemplate bean. We can use this bean to send http request to call payment service.
     *               And @loadBalanced annotation is used to make the RestTemplate load balance.
     *               The default algorithm is round-robin. And we can choose other algorithm like: random, leastActive, etc.
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
//        return converter;
//    }
}
