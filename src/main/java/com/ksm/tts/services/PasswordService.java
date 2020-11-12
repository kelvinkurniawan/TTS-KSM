/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.EmailInput;
import com.ksm.tts.entities.EmailOutput;
import com.ksm.tts.entities.PasswordInput;
import com.ksm.tts.entities.PasswordOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service

public class PasswordService {

    @Autowired
    RestTemplate restTemplate;

    private final String uri = "http://116.254.101.228:8080/ma_test/";

    public boolean performGetEmail(EmailInput emailInput) {
        try {
            restTemplate.getForObject(uri + "forgotpassword/" + emailInput.getEmail(), EmailOutput.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }
    
    public boolean performPostNewPassword(PasswordInput passwordInput, String verificationCode){
        try {
            restTemplate.put(uri + "forgotpassword/" + verificationCode, passwordInput, PasswordOutput.class);
            return true;
        }catch(RestClientException e){
            return false;
        }
    }
}
