package com.example.calenderBackEnd.User;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String password;
    private String email;
}
