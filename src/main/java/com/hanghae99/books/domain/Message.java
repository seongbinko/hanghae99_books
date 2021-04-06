package com.hanghae99.books.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Message {
    private String message1;

    @Builder
    public Message(String message1, String message2){
        this.message1 = message1;
    }
}
