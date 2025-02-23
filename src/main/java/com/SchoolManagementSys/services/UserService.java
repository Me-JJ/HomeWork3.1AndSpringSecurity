package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.SignUpDto;
import com.SchoolManagementSys.dto.UserDto;
import com.SchoolManagementSys.entity.UserEntity;
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

    public UserEntity getUserById(Long userId)
    {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound(("User not found with userId -> "+userId)));
    }
}
