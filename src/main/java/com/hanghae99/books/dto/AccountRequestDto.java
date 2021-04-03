package com.hanghae99.books.dto;

import lombok.Getter;

@Getter
public class AccountRequestDto {
    private String username;
    private String password;
    private String checkpw;
}
