package com.hanghae99.books.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class Message {
    private String message1;


    public Message(String message1){
        this.message1 = message1;
    }
}
