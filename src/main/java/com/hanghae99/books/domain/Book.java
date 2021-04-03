package com.hanghae99.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Long id;

    private String imgUrl;

    @Column(nullable = false)
    private String title;

    private String bookElement;

    @Lob
    @Column(nullable = false)
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Comment> comments = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<BookLike> bookLikes = new HashSet<>();

}
