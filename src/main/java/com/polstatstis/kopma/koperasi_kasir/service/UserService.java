package com.polstatstis.kopma.koperasi_kasir.service;

import com.polstatstis.kopma.koperasi_kasir.dto.UserDto;

public interface UserService {

    public UserDto createUser(UserDto user);

    public UserDto getUserByEmail(String email);
}
