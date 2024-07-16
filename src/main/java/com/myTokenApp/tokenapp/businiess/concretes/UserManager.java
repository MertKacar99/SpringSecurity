package com.myTokenApp.tokenapp.businiess.concretes;

import com.myTokenApp.tokenapp.businiess.abstracts.UserService;
import com.myTokenApp.tokenapp.businiess.requests.ChangePasswordUserRequest;
import com.myTokenApp.tokenapp.businiess.requests.CreateUserRequest;
import com.myTokenApp.tokenapp.businiess.responses.GetAllByIdUserResponse;
import com.myTokenApp.tokenapp.businiess.responses.GetAllUserResponse;
import com.myTokenApp.tokenapp.model.User;
import com.myTokenApp.tokenapp.modelAcess.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService , UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public  UserManager(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder
                    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
 ;
    }


    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> responseUsers = new ArrayList<>();
        for (User user : users) {
            GetAllUserResponse userResponseItem = new GetAllUserResponse();
            userResponseItem.setId(user.getId());
            userResponseItem.setName(user.getName());
            userResponseItem.setUsername(user.getUsername());
            responseUsers.add(userResponseItem);
        }
        return responseUsers;

    }

    @Override
    public GetAllByIdUserResponse getById(Long id) {
      User user = userRepository.findById(id).orElseThrow();
        GetAllByIdUserResponse response = new GetAllByIdUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        return  response;
    }

    @Override
    public void Add(CreateUserRequest createUserRequest ) {
         User user = new User();
         user.setName(createUserRequest.name());
         user.setUsername(createUserRequest.username());
         user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.password()));
         user.setAuthorities(createUserRequest.authorities());
        userRepository.save(user);

    }
    //Folsdev Yöntemi.BUILDER
    @Override
    public User createUser(CreateUserRequest createUserRequest ) {
        User user =  User.builder()
                .name(createUserRequest.name())
                .username(createUserRequest.username())
                .password(bCryptPasswordEncoder.encode(createUserRequest.password()))
                .authorities(createUserRequest.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();
        return userRepository.save(user);
    }
    @Transactional
    @Override
    public void delete(Long id) {
       userRepository.findById(id).ifPresent(user -> {
           userRepository.delete(user);
       });
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user = userRepository.findByUsername(username);
       return  user.orElseThrow(EntityExistsException::new);
    }
}
