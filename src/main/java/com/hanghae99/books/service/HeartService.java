package com.hanghae99.books.service;

import com.hanghae99.books.domain.Account;
import com.hanghae99.books.domain.Book;
import com.hanghae99.books.domain.Heart;
import com.hanghae99.books.repository.AccountRepository;
import com.hanghae99.books.repository.BookRepository;
import com.hanghae99.books.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;

    public HashMap<String, Object> ReadHeart(Long book_id, Long account_id) {
        Heart heart = heartRepository.findByBookIdAndAccountId(book_id, account_id);
        List<Heart> heartCount = heartRepository.findByBookId(book_id);
        HashMap<String, Object> map = new HashMap<>();
        Integer Count = heartCount.size();
        if (heart == null){
            map.put("check", false);
            map.put("heartCount", Count);
            return map;
        }
        map.put("check", true);
        map.put("heartCount", Count);
        return map;
    }

    @Transactional
    public Heart CreateHeart(Long book_id, Long account_id){
        Book book = bookRepository.findById(book_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBookIdAndAccountId(book_id, account_id);
        if (heart == null) {
            Heart newHeart = new Heart();
            newHeart.setHeart(true);
            book.addHeart(newHeart);
            account.addHeart(newHeart);
            heartRepository.save(newHeart);
            return newHeart;
        }
        return null;
    }

    public Heart DeleteHeart(Long book_id, Long account_id){
        Book book = bookRepository.findById(book_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다.")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBookIdAndAccountId(book_id, account_id);
        if (heart != null){
            book.deleteHeart(heart);
            account.deleteHeart(heart);
            heartRepository.deleteById(heart.getId());
            return heart;
        }
        return null;
    }
}
