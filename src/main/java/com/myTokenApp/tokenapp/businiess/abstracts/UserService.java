package com.myTokenApp.tokenapp.businiess.abstracts;

import com.myTokenApp.tokenapp.businiess.concretes.JwtManager;
import com.myTokenApp.tokenapp.businiess.requests.ChangePasswordUserRequest;
import com.myTokenApp.tokenapp.businiess.requests.CreateUserRequest;
import com.myTokenApp.tokenapp.businiess.responses.GetAllByIdUserResponse;
import com.myTokenApp.tokenapp.businiess.responses.GetAllUserResponse;
import com.myTokenApp.tokenapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface UserService  extends UserDetailsService {
    //create yapar.
    List<GetAllUserResponse> getAll();
    GetAllByIdUserResponse getById(Long id);
    void Add(CreateUserRequest  createUserRequest);
    User createUser(CreateUserRequest  createUserRequest);
    void delete(Long id);

}
