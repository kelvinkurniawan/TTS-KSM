/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.config;

import com.ksm.tts.entities.LoginInput;
import com.ksm.tts.entities.LoginOutput;
import com.ksm.tts.services.LoginService;
import com.ksm.tts.utils.InformationUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author kelvi
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    boolean shouldAuthenticateAgainstThirdPartySystem = true;
    
    @Autowired
    LoginService loginService;
    
    LoginInput loginInput = new LoginInput();
    LoginOutput loginOutput;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName(); //email
        String password = authentication.getCredentials().toString();
      
        loginInput.setEmail(name);
        loginInput.setPassword(password);
        
        loginOutput = loginService.login(loginInput);
        
        if (loginOutput.getStatus().equalsIgnoreCase("Verified")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(loginOutput.getUser().getRoles().get(0)));
            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            
            InformationUser.userId = loginOutput.getUser().getId();
            
            return auth;
        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
