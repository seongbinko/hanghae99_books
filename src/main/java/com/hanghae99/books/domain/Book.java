package com.hanghae99.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private Set<Heart> hearts = new HashSet<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setBook(this);
    }

    public void deleteComment(Comment deleteComment){
        this.comments.remove(deleteComment);
        deleteComment.setBook(null);
    }

}
