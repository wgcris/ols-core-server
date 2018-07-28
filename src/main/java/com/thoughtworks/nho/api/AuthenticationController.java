package com.thoughtworks.nho.api;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import com.thoughtworks.nho.service.AuthService;
import com.thoughtworks.nho.util.ResponseInfo;
import com.thoughtworks.nho.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        authService.logout(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseInfo login(@RequestBody LoginRequestUser loginRequestUser, HttpServletResponse response) {
        JWTUser jwtUser = authService.login(response, loginRequestUser);
        return new ResponseInfo(ResultCode.SUCCESS.getCode(),jwtUser);
    }
}
