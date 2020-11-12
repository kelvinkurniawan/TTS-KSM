/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.entities.profile.Address;
import com.ksm.tts.entities.profile.Basic;
import com.ksm.tts.entities.profile.Contact;
import com.ksm.tts.entities.profile.CurrentOccupation;
import com.ksm.tts.entities.profile.Education;
import com.ksm.tts.services.MajorService;
import com.ksm.tts.services.ProfileService;
import com.ksm.tts.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kelvi
 */
@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    MajorService majorService;

    @Autowired
    UniversityService universityService;

    private final String tempId = "USER-00022";

    @GetMapping("/profile/")
    public String profileBasic(Model model) {
        model.addAttribute("basic", profileService.getProfileBasic(tempId));
        model.addAttribute("address", profileService.getProfileAddress(tempId));
        model.addAttribute("contact", profileService.getProfileContact(tempId));
        model.addAttribute("occupation", profileService.getProfileOccupation(tempId));
        model.addAttribute("education", profileService.getProfileEducation(tempId));
        model.addAttribute("majors", majorService.getAll());
        model.addAttribute("universities", universityService.getAll());
        return "profiles/profile";
    }

    @PostMapping("/profile/basic/perform_update")
    public String profileBasicPerformUpdate(Basic basic) {
        profileService.saveProfileBasic(basic);
        return "redirect:/profile/";
    }

    @PostMapping("/profile/address/perform_update")
    public String profileAddressPerformUpdate(Address address) {
        profileService.saveProfileAddress(address);
        return "redirect:/profile/#address";
    }

    @PostMapping("/profile/contact/perform_update")
    public String profileContactPerformUpdate(Contact contact) {
        profileService.saveProfileContact(contact);
        return "redirect:/profile/#contact";
    }

    @PostMapping("/profile/currentoccupation/perform_update")
    public String profileProfilePerformUpdate(CurrentOccupation currentOccupation) {
        profileService.saveProfileOccupation(currentOccupation);
        return "redirect:/profile/#occupation";
    }

    @PostMapping("/profile/education/perform_update")
    public String profileEducationPerformUpdate(Education education) {
        profileService.saveProfileEducation(education);
        return "redirect:/profile/#education";
    }

}
