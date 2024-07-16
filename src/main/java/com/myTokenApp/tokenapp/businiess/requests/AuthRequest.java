package com.myTokenApp.tokenapp.businiess.requests;


public record AuthRequest (
        String username,
        String password
){
}
