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
public class Heart {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isHeart;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Account account;

}
