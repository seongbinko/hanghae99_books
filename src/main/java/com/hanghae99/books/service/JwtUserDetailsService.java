package com.hanghae99.books.service;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.JwtRequest;
import com.hanghae99.books.repository.AccountRepository;
import com.hanghae99.books.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }else{
            return new UserDetailsImpl(user);
        }
    }
}
