package com.hanghae99.books.service;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.dto.AccountRequestDto;
import com.hanghae99.books.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Account registerAccount(AccountRequestDto requestDto){
        String username = requestDto.getUsername();

        Optional<Account> found = accountRepository.findByUsername(username);
        if(found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }

        if(!requestDto.getPassword().equals(requestDto.getCheckpw())){
            throw new IllegalArgumentException("pw가 일치하지 않습니다.");
        }
        //패스워드 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());
        Account account = new Account(username, password);
        accountRepository.save(account);
        return account;
    }
}
