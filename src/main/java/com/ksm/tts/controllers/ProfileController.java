/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kelvi
 */
@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;
    
    private final String tempId = "USER-00022";
    
    @GetMapping("/profile/")
    public String profilBasic(Model model) {
        model.addAttribute("basic", profileService.getProfilBasic(tempId));
        model.addAttribute("address", profileService.getProfilAddress(tempId));
        model.addAttribute("contact", profileService.getProfilContact(tempId));
        model.addAttribute("occupation", profileService.getProfilOccupation(tempId));
        model.addAttribute("education", profileService.getProfilEducation(tempId));
        System.out.println(profileService.getProfilBasic(tempId));
        return "profiles/profile";
    }
    
    @GetMapping("/profile/address/")
    public String profilAddress(Model model) {
        model.addAttribute("profil", profileService.getProfilAddress(tempId));
        System.out.println(profileService.getProfilAddress(tempId));
        return "profiles/address";
    }
    
    @GetMapping("/profile/contact/")
    public String profilContact(Model model) {
        model.addAttribute("profil", profileService.getProfilContact(tempId));
        System.out.println(profileService.getProfilContact(tempId));
        return "profiles/contact";
    }
    
    @GetMapping("/profile/currentoccupation/")
    public String profilCurrentOccupation(Model model) {
        model.addAttribute("profil", profileService.getProfilOccupation(tempId));
        System.out.println(profileService.getProfilOccupation(tempId));
        return "profiles/occupation";
    }
    
    @GetMapping("/profile/education/")
    public String profilEducation(Model model) {
        model.addAttribute("profil", profileService.getProfilEducation(tempId));
        System.out.println(profileService.getProfilEducation(tempId));
        return "profiles/education";
    }
}
