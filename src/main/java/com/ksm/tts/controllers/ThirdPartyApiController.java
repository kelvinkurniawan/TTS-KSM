/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.entities.City;
import com.ksm.tts.entities.Province;
import com.ksm.tts.services.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kelvi
 */
@Controller
public class ThirdPartyApiController {
    
    @Autowired
    LocationService locationService;
    
    @ResponseBody
    @GetMapping("/api/getprovince")
    public List<Province> getProvince() {
        return locationService.getAllProvince(locationService.getProvince());
    }
    
    @ResponseBody
    @GetMapping("/api/getcity/{id}")
    public List<City> getCity(@PathVariable("id") int id) {
        return locationService.getAllCity(locationService.getCity(id));
    }
}
