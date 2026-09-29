package com.libiao.lblearn1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libiao.lblearn1.common.result.Result;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public Result login() {
        return Result.Success(null);
    }

    @PostMapping("/register")
    public Result register() {
        return Result.Success(null);
    }
}
