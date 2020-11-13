package com.jan.VOD.security;

public enum ApplicationUserPermission {
    NORMALUSER_READ("student:read"),
    NORMALUSER_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private  final String permission;
    ApplicationUserPermission(String permission){ this.permission = permission; }
    public String getPermission(){
        return permission;
    }
}
