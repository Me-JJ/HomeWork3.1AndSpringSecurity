package com.SchoolManagementSys.entity;

import com.SchoolManagementSys.entity.enums.Permission;
import com.SchoolManagementSys.entity.enums.Role;
import com.SchoolManagementSys.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role ->
        {
            authorities.addAll(PermissionMapping.getAuthoritiesByRole(role));
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
