package com.rizalfadiaalfikri.echosphere.services;

import java.util.List;

import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.req.UsersDto;

public interface UserService {

    public Users registerUser(UsersDto usersDto);

    public Users findUserById(Long id);

    public Users findUserByEmail(String email);

    public Users followuser(Long reqUserId, Long followUserId);

    public Users updateUser(UsersDto usersDto, Long id);

    public List<Users> searchUser(String query);

}
