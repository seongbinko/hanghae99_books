package com.hanghae99.books.domain;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookLike {

    @Id
    @GeneratedValue
    private Long id;

    private int likeCount;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Account account;

}
