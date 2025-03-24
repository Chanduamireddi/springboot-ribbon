package com.chandu.wk10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
public class MyClientSideController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/client/frontend")
    public String hi() {
        String randomString = this.restTemplate.getForObject("http://ribbon-server/backend", String.class);
        return "Server Response :: " + randomString;
    }
}
