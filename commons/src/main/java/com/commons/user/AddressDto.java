package com.commons.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String addressId;

    private String userId;

    private String addressLine1;

    private String addressLine2;

    private String country;

    private String state;

    private String pinCode;

    private String city;
}
