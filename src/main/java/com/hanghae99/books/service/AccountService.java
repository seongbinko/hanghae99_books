package com.hanghae99.books.service;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.dto.AccountRequestDto;
import com.hanghae99.books.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Account registerAccount(AccountRequestDto requestDto){
        return accountRepository.save(
                Account.builder()
                    .username(requestDto.getUsername())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build()
        );
    }
}
