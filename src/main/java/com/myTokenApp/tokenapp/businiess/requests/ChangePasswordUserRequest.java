package com.myTokenApp.tokenapp.businiess.requests;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChangePasswordUserRequest {
    private   String currentPassword;
    private   String newPassword;
    private   String username;
}
