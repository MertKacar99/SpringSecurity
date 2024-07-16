package com.myTokenApp.tokenapp.businiess.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllByIdUserResponse {
    private Long id;
    private String name;
    private String username;

}
