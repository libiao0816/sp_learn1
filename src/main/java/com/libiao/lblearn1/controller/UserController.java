package com.libiao.lblearn1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libiao.lblearn1.common.result.Result;
import com.libiao.lblearn1.domain.dto.user.UserLoginDTO;
import com.libiao.lblearn1.domain.dto.user.UserRegisterDTO;
import com.libiao.lblearn1.domain.po.User;
import com.libiao.lblearn1.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/detail")
    public Result<User> detail() {
        User user = userService.getUserDetail();
        return Result.Success(user);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        return Result.Success(token);
    }

    @PostMapping("/register")
    public Result<Integer> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        Integer insert = userService.register(userRegisterDTO);
        return Result.Success(insert);
    }

    @PostMapping("/logout")
    public Result<Boolean> logout(@RequestHeader("Authorization") String token) {
        Boolean logout = userService.logout(token);
        return Result.Success(logout);
    }
}
