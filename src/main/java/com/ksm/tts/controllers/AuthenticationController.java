/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.entities.LoginInput;
import com.ksm.tts.entities.RegisterInput;
import com.ksm.tts.services.LoginService;
import com.ksm.tts.services.MajorService;
import com.ksm.tts.services.RegisterService;
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
public class AuthenticationController {

    @Autowired
    LoginService loginService;

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
    
    @Autowired
    RegisterService registerService;    
    
    @Autowired
    MajorService majorService;
    
    @Autowired
    UniversityService universityService;
    
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
}
