package com.SchoolManagementSys.utils;

import com.SchoolManagementSys.entity.enums.Permission;
import com.SchoolManagementSys.entity.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionMapping
{
    private static final Map<Role, Set<Permission>> permissionMapping = Map.of(
            Role.PROF,Set.of(Permission.PROF_VIEW,
                    Permission.STUDENT_VIEW,
                    Permission.SUBJECT_VIEW,
                    Permission.ADMISSION_RECORD_VIEW),

            Role.STUDENT,Set.of(Permission.PROF_VIEW,
                    Permission.STUDENT_VIEW,
                    Permission.SUBJECT_VIEW),

            Role.ADMIN,Set.of(Permission.PROF_VIEW,
                    Permission.STUDENT_VIEW,
                    Permission.SUBJECT_VIEW,
                    Permission.ADMISSION_RECORD_VIEW,
                    Permission.UPDATE,
                    Permission.CREATE,
                    Permission.READ,
                    Permission.DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesByRole(Role role)
    {
        return permissionMapping.get(role).stream().map(x -> new SimpleGrantedAuthority(x.name())).collect(Collectors.toSet());
    }

}
