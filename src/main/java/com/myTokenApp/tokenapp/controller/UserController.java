package com.myTokenApp.tokenapp.controller;


import com.myTokenApp.tokenapp.Rules.TokenAuthenticationRules;
import com.myTokenApp.tokenapp.businiess.abstracts.UserService;
import com.myTokenApp.tokenapp.businiess.concretes.JwtManager;
import com.myTokenApp.tokenapp.businiess.requests.AuthRequest;

import com.myTokenApp.tokenapp.businiess.requests.CreateUserRequest;
import com.myTokenApp.tokenapp.businiess.responses.GetAllByIdUserResponse;
import com.myTokenApp.tokenapp.businiess.responses.GetAllUserResponse;
import com.myTokenApp.tokenapp.model.User;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j

public class UserController {

    private final UserService userService;
    private final JwtManager jwtManager;
    private final AuthenticationManager authenticationManager;
    private final TokenAuthenticationRules tokenAuthenticationRules;

    public UserController(UserService userService, JwtManager jwtManager, AuthenticationManager authenticationManager, TokenAuthenticationRules tokenAuthenticationRules) {
        this.userService = userService;
        this.jwtManager = jwtManager;
        this.authenticationManager = authenticationManager;
        this.tokenAuthenticationRules = tokenAuthenticationRules;
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "Hoş Geldin....";

    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin/GetByIdUser/{id}")
    public GetAllByIdUserResponse getbyId(@PathVariable Long id) {
        return this.userService.getById(id);
    }

    @GetMapping("/admin/getAllUsers")
    public List<GetAllUserResponse> getAllUsers() {
        return userService.getAll();
    }


    @PostMapping("/addNewUser")
    public User addUser(@RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }


    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        return tokenAuthenticationRules.generateToken(request);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
        log.info("Kullanıcı silindi: " + id);
    }






}
