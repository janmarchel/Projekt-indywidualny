package com.jan.VOD.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.jan.VOD.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    NORMALUSER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, NORMALUSER_READ, NORMALUSER_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,NORMALUSER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
    public  Set<ApplicationUserPermission> getPermissions(){return permissions;}

    public  Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE" + this.name()));
        return permissions;
    }
}
