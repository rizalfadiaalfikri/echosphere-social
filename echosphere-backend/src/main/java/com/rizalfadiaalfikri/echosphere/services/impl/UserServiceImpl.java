package com.rizalfadiaalfikri.echosphere.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.models.entity.Roles;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.req.UsersDto;
import com.rizalfadiaalfikri.echosphere.repository.RoleRepository;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.services.UserService;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.BadRequestException;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users registerUser(UsersDto usersDto) {

        if (userRepository.findByEmail(usersDto.getEmail()).isPresent()) {
            throw new BadRequestException("User with email " + usersDto.getEmail() + " is already registered");
        }

        Users newUsers = new Users();
        newUsers.setFirstName(usersDto.getFirstName());
        newUsers.setLastName(usersDto.getLastName());
        newUsers.setEmail(usersDto.getEmail());
        newUsers.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        newUsers.setGender(usersDto.getGender());

        // Set Roles
        Collection<Roles> roles = roleRepository.findAllById(Collections.singletonList(1L));
        newUsers.setRoles(roles);

        Users savedUser = userRepository.save(newUsers);
        return savedUser;
    }

    @Override
    public Users findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RowNotFoundException("Users with id " + id + " is not found"));
    }

    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RowNotFoundException("Users with email " + email + " is not found"));
    }

    @Override
    public Users followuser(Long reqUserId, Long followUserId) {
        Users user1 = findUserById(followUserId);
        Users user2 = findUserById(reqUserId);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public Users updateUser(UsersDto usersDto, Long id) {
        Users existingUser = userRepository.findById(id).orElseThrow(
                () -> new RowNotFoundException("Users with id " + id + " is not found"));

        existingUser.setEmail(usersDto.getEmail());
        existingUser.setFirstName(usersDto.getFirstName());
        existingUser.setLastName(usersDto.getLastName());
        existingUser.setPassword(usersDto.getPassword());
        existingUser.setGender(usersDto.getGender());

        return userRepository.save(existingUser);

    }

    @Override
    public List<Users> searchUser(String query) {
        return userRepository.searchUsers(query);
    }

}
