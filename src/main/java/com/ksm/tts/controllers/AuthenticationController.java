/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.entities.EmailInput;
import com.ksm.tts.entities.PasswordInput;
import com.ksm.tts.entities.RegisterInput;
import com.ksm.tts.entities.RegisterOutput;
import com.ksm.tts.services.LoginService;
import com.ksm.tts.services.MajorService;
import com.ksm.tts.services.PasswordService;
import com.ksm.tts.services.RegisterService;
import com.ksm.tts.services.UniversityService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", " Register New Account ");
        model.addAttribute("person", new RegisterInput());
        model.addAttribute("major", majorService.getAll());
        model.addAttribute("university", universityService.getAll());
        return "register";
    }

    @PostMapping("/register/perform_register")
    public String register(RegisterInput input) {

        RegisterOutput registerOutput = registerService.register(input);
        
        if(registerOutput.isStatus()){
            return "redirect:/?result=register_success";
        }else{
            return "redirect:/register/?error=" + registerOutput.getMessage();
        }
        
    }

    @GetMapping("/forgotpassword")
    public String forgotPasswordInputEmail(Model model) {
        model.addAttribute("title", " Reset Password ");
        model.addAttribute("email", new PasswordInput());
        return "forgotpassword/forgotpasswordinputemail";
    }

    @PostMapping("/forgotpassword/perform_send_email")
    public String performSendEmail(EmailInput emailInput) {
        passwordService.performGetEmail(emailInput);
        return "redirect:/forgotpassword?result=success";
    }

    @GetMapping("/forgotpassword/{verificationCode}")
    public String forgotPasswordInputNewPassword(@PathVariable("verificationCode") String verificationCode, Model model) {
        model.addAttribute("title", " Change Password ");
        model.addAttribute("password", new PasswordInput());
        model.addAttribute("verificationCode", verificationCode);
        return "forgotpassword/forgotpasswordinputnewpassword";
    }

    @PostMapping("forgotpassword/perform_update/{verificationCode}")
    public String performPasswordUpdate(@PathVariable("verificationCode") String verificationCode, PasswordInput passwordInput) {
        passwordService.performPostNewPassword(passwordInput, verificationCode);
        return "redirect:/?result=passwordchanged";
    }
}
