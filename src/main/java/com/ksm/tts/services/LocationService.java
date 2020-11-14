/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.City;
import com.ksm.tts.entities.CityOutput;
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
    City city;
    CityOutput cityOutput;
    
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
    
    public CityOutput getCity(int id) {
        CityOutput result;
        
        ResponseEntity<CityOutput> responseEntity = restTemplate.exchange(
                "https://dev.farizdotid.com/api/daerahindonesia/kota?id_provinsi=" + id, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<CityOutput>() {
        });
        
        result = responseEntity.getBody();
        return result;
    }
    
    public List getAllCity(CityOutput output) {
        return output.getKota_kabupaten();
    }
    
}
