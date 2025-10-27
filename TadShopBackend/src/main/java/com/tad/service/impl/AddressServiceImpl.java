package com.tad.service.impl;

import com.tad.dto.exception.ResourceNotFoundException;
import com.tad.dto.mapper.AddressMapper;
import com.tad.dto.model.AddressDto;
import com.tad.entity.Address;
import com.tad.entity.User;
import com.tad.repositories.AddressRepository;
import com.tad.repositories.UserRepository;
import com.tad.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;
    private UserRepository userRepository;

    @Override
    public AddressDto createAddress(long userId, AddressDto addressDto) {
        Address address = addressMapper.mapToEntity(addressDto);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

        address.setUser(user);

        Address newAddress = addressRepository.save(address);
        return addressMapper.mapToDto(newAddress);
    }

    @Override
    public List<AddressDto> getAddressByUserId(long userId) {
        return List.of();
    }

    @Override
    public AddressDto updateAddress(long userId, AddressDto addressDto, long id) {
        return null;
    }

    @Override
    public String deleteAddress(long id) {
        return "";
    }
}
