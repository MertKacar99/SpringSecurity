package com.myTokenApp.tokenapp.Rules;

import com.myTokenApp.tokenapp.businiess.abstracts.UserService;
import com.myTokenApp.tokenapp.businiess.concretes.JwtManager;
import com.myTokenApp.tokenapp.businiess.requests.AuthRequest;
import com.myTokenApp.tokenapp.controller.UserController;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Slf4j 
public class TokenAuthenticationRules {
    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;

    @Autowired
    public TokenAuthenticationRules( AuthenticationManager authenticationManager, JwtManager jwtManager) {
        this.authenticationManager = authenticationManager;
        this.jwtManager = jwtManager;
    }

    public String generateToken(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
            if (authentication.isAuthenticated()) {
                return jwtManager.generateToken(authRequest.username());
            } else {
                log.info("invalid username:" + authRequest.username());
                throw new UsernameNotFoundException("invalid username {}" + authRequest.username());
            }

        } catch (Exception e) {
            log.error("Exception during token generation: " + e.getMessage());
            return null;
        }
    }
}
