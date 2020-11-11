/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.Majors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service
public class UniversityService {
        
    @Autowired
    RestTemplate restTemplate;
    
    private final String uri = "http://116.254.101.228:8080/ma_test/";
    
    public List<Majors> getAll(){
        List<Majors> result;

        ResponseEntity<List<Majors>> response = restTemplate.exchange(
                uri + "get/universities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Majors>>() {
        });

        result = response.getBody();
        return result;
    }
    
}
