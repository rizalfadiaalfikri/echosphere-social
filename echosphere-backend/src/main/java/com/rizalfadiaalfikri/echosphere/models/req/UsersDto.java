package com.rizalfadiaalfikri.echosphere.models.req;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
}
