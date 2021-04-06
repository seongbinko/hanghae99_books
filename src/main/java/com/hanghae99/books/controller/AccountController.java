package com.hanghae99.books.controller;


import com.google.gson.JsonObject;
import com.hanghae99.books.config.JwtTokenProvider;
import com.hanghae99.books.domain.Account;
import com.hanghae99.books.dto.AccountRequestDto;
import com.hanghae99.books.repository.AccountRepository;
import com.hanghae99.books.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AccountController {


    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountRepository accountRepository;

    @PostMapping("/api/signup")
    public ResponseEntity registerAccount(@RequestBody AccountRequestDto requestDto){
        Optional<Account> found = accountRepository.findByUsername(requestDto.getUsername());
        if(found.isPresent()){
            return ResponseEntity.badRequest().build();
            //throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        if(!requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            return ResponseEntity.badRequest().build();
            //throw new IllegalArgumentException("pw가 일치하지 않습니다.");
        }

        accountService.registerAccount(requestDto);
        return ResponseEntity.ok().build();
    }

    // 로그인
    @PostMapping("/api/login")
    public String login(@RequestBody Map<String, String> account) {

        Account acc = accountRepository.findByUsername(account.get("username")).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!passwordEncoder.matches(account.get("password"), acc.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        JsonObject obj = new JsonObject();
        obj.addProperty("token", jwtTokenProvider.createToken(acc.getUsername(), acc.getRoles()));
        return obj.toString();
    }
}
