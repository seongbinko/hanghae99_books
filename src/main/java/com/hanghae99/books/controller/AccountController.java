package com.hanghae99.books.controller;

import com.hanghae99.books.dto.AccountRequestDto;
import com.hanghae99.books.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String index(){
        return "ok";
    }

    @PostMapping("/user/signup")
    public String registerAccount(@RequestBody AccountRequestDto requestDto){
        accountService.registerAccount(requestDto);
        return "ok";
    }
}
