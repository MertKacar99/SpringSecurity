package com.myTokenApp.tokenapp.businiess.requests;

import com.myTokenApp.tokenapp.model.Role;
import lombok.Data;

import java.util.Set;
public record CreateUserRequest (
        String name,
        String username,
        String password,
        Set<Role> authorities
){
}
