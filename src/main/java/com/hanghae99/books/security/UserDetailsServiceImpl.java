//package com.hanghae99.books.security;
//
//import com.hanghae99.books.domain.Account;
//import com.hanghae99.books.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account user = accountRepository.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }else{
//            return new UserDetailsImpl(user);
//        }
//
//
//    }
//}
