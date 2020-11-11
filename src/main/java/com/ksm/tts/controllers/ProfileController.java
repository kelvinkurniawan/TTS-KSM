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
    
    @GetMapping("/profil/")
    public String profilBasic(Model model) {
        model.addAttribute("profil", profileService.getProfilBasic(tempId));
        System.out.println(profileService.getProfilBasic(tempId));
        return "profile";
    }
    
    @GetMapping("/profil/address/")
    public String profilAddress(Model model) {
        model.addAttribute("profil", profileService.getProfilAddress(tempId));
        System.out.println(profileService.getProfilAddress(tempId));
        return "profil_address";
    }
    
    @GetMapping("/profil/contact/")
    public String profilContact(Model model) {
        model.addAttribute("profil", profileService.getProfilContact(tempId));
        System.out.println(profileService.getProfilContact(tempId));
        return "profil_contact";
    }
    
    @GetMapping("/profil/currentoccupation/")
    public String profilCurrentOccupation(Model model) {
        model.addAttribute("profil", profileService.getProfilOccupation(tempId));
        System.out.println(profileService.getProfilOccupation(tempId));
        return "profil_occupation";
    }
    
    @GetMapping("/profil/education/")
    public String profilEducation(Model model) {
        model.addAttribute("profil", profileService.getProfilEducation(tempId));
        System.out.println(profileService.getProfilEducation(tempId));
        return "profil_edu";
    }
}
