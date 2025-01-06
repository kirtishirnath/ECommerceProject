package com.commons.user;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDto extends AuditableDto{

    private String userId;

    private String userName;

    private String password;

    private String role = "BUYER";

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
