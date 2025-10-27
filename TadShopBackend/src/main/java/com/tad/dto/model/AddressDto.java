package com.tad.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private long id;

    private String name;
    private String phone;
    private String company;
    private String province;
    private String district;
    private String ward;
    private String address;
    private String addressType;
}