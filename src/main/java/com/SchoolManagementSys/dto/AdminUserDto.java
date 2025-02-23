package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.enums.Permission;
import com.SchoolManagementSys.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Getter
@Setter
public class AdminUserDto
{
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;
    private Set<SimpleGrantedAuthority>authorities;
}
