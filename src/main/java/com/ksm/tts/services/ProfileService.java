/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.profile.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service
public class ProfileService {

    @Autowired
    RestTemplate restTemplate;

    private final String uri = "http://116.254.101.228:8080/ma_test/";

    public Basic getProfilBasic(String id) {

        Basic output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/basic/{id}", Basic.class, param);

        return output;
    }

    public Address getProfilAddress(String id) {

        Address output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/address/{id}", Address.class, param);

        return output;
    }

    public Contact getProfilContact(String id) {

        Contact output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/contact/{id}", Contact.class, param);

        return output;
    }

    public CurrentOccupation getProfilOccupation(String id) {

        CurrentOccupation output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/currentoccupation/{id}", CurrentOccupation.class, param);

        return output;
    }

    public Education getProfilEducation(String id) {

        Education output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/education/{id}", Education.class, param);

        return output;
    }
}
