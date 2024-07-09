package com.example.TheNews.entity;

import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long comment_id;

    @Column(name = "comment_text", length = 1000)
    private String comment_text;

    @Column(name = "comment_date")
    private java.sql.Timestamp commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userC")
    private UserEntity userC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleC")
    private ArticleEntity articleC;


    @Override
    public String toString() {
        return "User [id=" + comment_id + ", date=" + commentDate + "]";
    }
}
