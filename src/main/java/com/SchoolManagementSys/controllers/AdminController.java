package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.dto.AdminUserDto;
import com.SchoolManagementSys.dto.UserDto;
import com.SchoolManagementSys.entity.UserEntity;
import com.SchoolManagementSys.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final UserService userService;

    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<AdminUserDto>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("/{userId}/makeAdmin")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminUserDto> makeUserAdmin(@PathVariable Long userId)
    {
        return ResponseEntity.ok(userService.updateUserRoleToAdmin(userId));
    }

    @PutMapping("/{userId}/makeProf")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminUserDto> makeUserProf(@PathVariable Long userId)
    {
        return ResponseEntity.ok(userService.updateUserRoleToProf(userId));
    }

    @PutMapping("/{userId}/makeStd")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminUserDto> makeUserStudent(@PathVariable Long userId)
    {
        return ResponseEntity.ok(userService.updateUserRoleToStudent(userId));
    }
}
