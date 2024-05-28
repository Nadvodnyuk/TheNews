package com.example.TheNews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long article_id;

    @Column(name = "title")
    private String title;

    @Column(name = "topics")
    private String topics;

    @Column(name = "article_text")
    private String article_text;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "like_num")
    private Integer like_num;

    @Column(name = "comment_num")
    private Integer comment_num;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publicationDate")
    private java.sql.Timestamp publicationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleC")
    @JsonIgnore
    private List<CommentEntity> comments;

    public Timestamp getPublication_date() {
        return publicationDate;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publicationDate = publication_date;
    }


    @Override
    public String toString() {
        return "User [id=" + article_id + ", title=" + title +
                ", article text=" + article_text + ", image=" + image_url +
                ", number of likes=" + like_num + ", number of comments=" + comment_num +
                ", publication date=" + publicationDate + "]";
    }
}
