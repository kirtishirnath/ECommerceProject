package com.commons.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;

    @Builder.Default
    private String role="BUYER";

    private String name;

    @Builder.Default
    private String isAdmin="N";
}
