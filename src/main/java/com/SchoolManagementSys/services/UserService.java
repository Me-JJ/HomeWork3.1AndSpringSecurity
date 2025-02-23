package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.AdminUserDto;
import com.SchoolManagementSys.dto.SignUpDto;
import com.SchoolManagementSys.dto.UserDto;
import com.SchoolManagementSys.entity.UserEntity;
import com.SchoolManagementSys.entity.enums.Role;
import com.SchoolManagementSys.exceptions.ResourceNotFound;
import com.SchoolManagementSys.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService
{
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(SignUpDto signUpDto)
    {
        if(userRepo.existsByEmail(signUpDto.getEmail()))
        {
            throw new BadCredentialsException("User already present with email -> "+signUpDto.getEmail());
        }
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return modelMapper.map(userRepo.save(modelMapper.map(signUpDto, UserEntity.class)),UserDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(() -> new BadCredentialsException(("User not found with username(email) -> "+username)));
    }

    public UserEntity getUserByEmail(String email)
    {
        return userRepo.findByEmail(email).orElse(null);
    }
    public UserEntity getUserById(Long userId)
    {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound(("User not found with userId -> "+userId)));
    }

    public UserEntity save(UserEntity newUser) {
        return userRepo.save(newUser);
    }


    public List<AdminUserDto> getAllUsers()
    {
        return userRepo.findAll().stream().map(x ->
                modelMapper.map(x,AdminUserDto.class)).toList();
    }

    public AdminUserDto updateUserRoleToAdmin(Long userId)
    {
        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User not found wiht id -> "+userId));
        user.getRoles().add(Role.ADMIN);
        return modelMapper.map(userRepo.save(user),AdminUserDto.class);
    }

    public AdminUserDto updateUserRoleToProf(Long userId)
    {
        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User not found wiht id -> "+userId));
        user.getRoles().add(Role.PROF);
        return modelMapper.map(userRepo.save(user),AdminUserDto.class);
    }

    public AdminUserDto updateUserRoleToStudent(Long userId)
    {
        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User not found wiht id -> "+userId));
        user.getRoles().add(Role.STUDENT);
        return modelMapper.map(userRepo.save(user),AdminUserDto.class);
    }



}
