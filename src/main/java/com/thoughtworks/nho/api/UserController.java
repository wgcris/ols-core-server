package com.thoughtworks.nho.api;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.service.UserService;
import com.thoughtworks.nho.util.ResponseInfo;
import com.thoughtworks.nho.util.ResultCode;
import com.thoughtworks.nho.util.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.thoughtworks.nho.cofiguration.security.APISecureRolePrivilege.CREATE_USER;
import static com.thoughtworks.nho.cofiguration.security.APISecureRolePrivilege.RETRIVE_USER;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseInfo create(@RequestBody User user) {
        JWTUser jwtUser =  UserFactory.fromUser(userService.create(user));
        return new ResponseInfo(ResultCode.SUCCESS.getCode(),jwtUser);
    }

    @GetMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseInfo find(@PathVariable String username) {
        JWTUser jwtUser =  UserFactory.fromUser(userService.findByName(username));
        return new ResponseInfo(ResultCode.SUCCESS.getCode(),jwtUser);
    }
}
