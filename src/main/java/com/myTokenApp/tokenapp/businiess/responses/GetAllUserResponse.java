package com.myTokenApp.tokenapp.businiess.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
    private Long id;
    private  String name;
    private String username;
}
