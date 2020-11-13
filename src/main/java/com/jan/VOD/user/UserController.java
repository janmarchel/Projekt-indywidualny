package com.jan.VOD.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final List<User> USERS = Arrays.asList(
            new User(1,"Jan Marchel"),
            new User(1,"Adam Marchel"),
            new User(3,"Lukasz Marchel")
            );
    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        return  USERS.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User " + userId + "does not exists"));
        }

}
