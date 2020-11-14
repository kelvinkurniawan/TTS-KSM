/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.Province;
import com.ksm.tts.entities.ProvinceOutput;
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
public class LocationService {
    
    Province province;
    ProvinceOutput provinceOutput;
    
    @Autowired
    RestTemplate restTemplate;
            
    
    public ProvinceOutput getProvince() {
        ProvinceOutput result;
        
        ResponseEntity<ProvinceOutput> responseEntity = restTemplate.exchange(
                "https://dev.farizdotid.com/api/daerahindonesia/provinsi", 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<ProvinceOutput>() {
        });
        
        result = responseEntity.getBody();
        return result;
    }
    
    public List getAllProvince(ProvinceOutput output) {
        return output.getProvinsi();
    }
}
