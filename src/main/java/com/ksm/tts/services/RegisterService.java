/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.RegisterInput;
import com.ksm.tts.entities.RegisterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service
public class RegisterService {
        
    @Autowired
    RestTemplate restTemplate;
   
    RegisterOutput registerOutput = new RegisterOutput();
    private final String uri = "http://116.254.101.228:8080/ma_test/";
    
    public RegisterOutput register(RegisterInput input) {
        
        try{
            restTemplate.postForEntity(uri + "register", input, RegisterInput.class);
            registerOutput.setStatus(true);
            registerOutput.setMessage("register_success");
            return registerOutput;
        }catch(HttpStatusCodeException e){
            registerOutput.setStatus(false);
            String message = e.getMessage().split(":")[1].replace("[", "").replace("]", "");
            registerOutput.setMessage(message);
            return registerOutput;
        }
    }
    
}
