package com.jan.VOD.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")

public class UserMenagmentController {
    private static final List<User> USERS = Arrays.asList(
            new User(1,"Jan Marchel"),
            new User(1,"Adam Marchel"),
            new User(3,"Lukasz Marchel")
    );
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_ADMINTRAINEE')")
    public List<User> getAllUsers() {
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewUser(@RequestBody User user){
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteUser(@PathVariable("userId") Integer userId){
        System.out.println(userId);
    }
    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(Integer userId, User user){
        System.out.println(String.format("%s %s", userId ,user));
    }
}
