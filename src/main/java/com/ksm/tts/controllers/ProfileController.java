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
import com.ksm.tts.utils.FileUploadUtil;
import com.ksm.tts.utils.InformationUser;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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



    @GetMapping("/")
    public String profileBasic(Model model) {
        model.addAttribute("title", " My Profile");
        model.addAttribute("basic", profileService.getProfileBasic(InformationUser.userId));
        model.addAttribute("address", profileService.getProfileAddress(InformationUser.userId));
        model.addAttribute("contact", profileService.getProfileContact(InformationUser.userId));
        model.addAttribute("occupation", profileService.getProfileOccupation(InformationUser.userId));
        model.addAttribute("education", profileService.getProfileEducation(InformationUser.userId));
        model.addAttribute("majors", majorService.getAll());
        model.addAttribute("universities", universityService.getAll());
        model.addAttribute("userId", InformationUser.userId);
        return "profiles/profile";
    }

    @GetMapping("/profile/changephoto")
    public String updatePhoto(Model model) {
        model.addAttribute("userId", InformationUser.userId);
        return "profiles/updatephoto";
    }

    @PostMapping("profile/changephoto/perform_update")
    public String profilePhotoPerformUpdate(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = InformationUser.userId + ".jpg";
        FileUploadUtil fileUpload = new FileUploadUtil();
        fileUpload.save(multipartFile, filename);
        return "redirect:/?result=update_success";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        FileUploadUtil fileUpload = new FileUploadUtil();
        Resource file = fileUpload.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/profile/basic/perform_update")
    public String profileBasicPerformUpdate(Basic basic) {
        profileService.saveProfileBasic(basic);
        return "redirect:/?result=update_success";
    }

    @PostMapping("/profile/address/perform_update")
    public String profileAddressPerformUpdate(Address address) {
        profileService.saveProfileAddress(address);
        return "redirect:/?result=update_success";
    }

    @PostMapping("/profile/contact/perform_update")
    public String profileContactPerformUpdate(Contact contact) {
        profileService.saveProfileContact(contact);
        return "redirect:/?result=update_success";
    }

    @PostMapping("/profile/currentoccupation/perform_update")
    public String profileProfilePerformUpdate(CurrentOccupation currentOccupation) {
        profileService.saveProfileOccupation(currentOccupation);
        return "redirect:/?result=update_success";
    }

    @PostMapping("/profile/education/perform_update")
    public String profileEducationPerformUpdate(Education education) {
        profileService.saveProfileEducation(education);
        return "redirect:/?result=update_success";
    }

}
