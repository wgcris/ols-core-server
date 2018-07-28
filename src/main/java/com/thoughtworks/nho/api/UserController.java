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

    private static final String USERNAME_CHECK = "^[a-zA-Z]{1,10}$";

    private static final String PASSWORD_CHECK = "^[0-9]{8,}$";

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseInfo create(@RequestBody User user) {
        if (user.getName() == null || !user.getName().matches(USERNAME_CHECK)) {
            return new ResponseInfo(ResultCode.USER_NAME_ERROR.getCode(), "必须是1-10位英文字符，不能有空格");
        }
        if (user.getPassword() == null || !user.getPassword().matches(USERNAME_CHECK)) {
            return new ResponseInfo(ResultCode.USER_PWD_ERROR.getCode(), "必须是大于8个的数字，不能有空格");
        }
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
