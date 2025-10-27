package com.tad.service;

import com.tad.dto.model.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(long userId, AddressDto addressDto);
    public List<AddressDto> getAddressByUserId(long userId);
    AddressDto updateAddress(long userId, AddressDto addressDto, long id);
    String deleteAddress(long id);
}
