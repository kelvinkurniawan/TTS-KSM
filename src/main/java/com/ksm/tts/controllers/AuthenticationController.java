/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.entities.EmailInput;
import com.ksm.tts.entities.LoginInput;
import com.ksm.tts.entities.PasswordInput;
import com.ksm.tts.entities.RegisterInput;
import com.ksm.tts.services.LoginService;
import com.ksm.tts.services.MajorService;
import com.ksm.tts.services.PasswordService;
import com.ksm.tts.services.RegisterService;
import com.ksm.tts.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kelvi
 */
@Controller
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @Autowired
    RegisterService registerService;

    @Autowired
    MajorService majorService;

    @Autowired
    UniversityService universityService;

    @Autowired
    PasswordService passwordService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("account", new LoginInput());
        return "index";
    }

    @PostMapping("/login")
    public String login(LoginInput input, Model model) {
        System.out.println(loginService.login(input));
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("person", new RegisterInput());
        model.addAttribute("major", majorService.getAll());
        model.addAttribute("university", universityService.getAll());
        return "register";
    }

    @PostMapping("/register/execute")
    public String register(RegisterInput input) {
        System.out.println(registerService.register(input));
        return "index";
    }

    @GetMapping("/forgotpassword")
    public String forgotPasswordInputEmail(Model model) {
        model.addAttribute("email", new PasswordInput());
        return "forgotpasswordinputemail";
    }
    
    @PostMapping("/forgotpassword/perform_send_email")
    public String performSendEmail(EmailInput emailInput){
        passwordService.performGetEmail(emailInput);
        return "redirect:/forgotpassword?result=success";
    }
    
    @GetMapping("/forgotpassword/{verificationCode}")
    public String forgotPasswordInputNewPassword(@PathVariable("verificationCode") String verificationCode, Model model){
        model.addAttribute("password", new PasswordInput());
        model.addAttribute("verificationCode", verificationCode);
        return "forgotpasswordinputnewpassword";
    }
    
    @PostMapping("forgotpassword/perform_update/{verificationCode}")
    public String performPasswordUpdate(@PathVariable("verificationCode") String verificationCode, PasswordInput passwordInput){
        passwordService.performPostNewPassword(passwordInput, verificationCode);
        return "redirect:/index?result=passwordchanged";
    }
}
