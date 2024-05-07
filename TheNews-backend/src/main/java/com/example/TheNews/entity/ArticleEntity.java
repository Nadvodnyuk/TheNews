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

    public long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(long article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getLike_num() {
        return like_num;
    }

    public void setLike_num(Integer like_num) {
        this.like_num = like_num;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    public Timestamp getPublication_date() {
        return publicationDate;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publicationDate = publication_date;
    }


    public List<CommentEntity> getComments() {
        return comments != null ? comments : Collections.emptyList();
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "User [id=" + article_id + ", title=" + title +
                ", article text=" + article_text + ", image=" + image_url +
                ", number of likes=" + like_num + ", number of comments=" + comment_num +
                ", publication date=" + publicationDate + "]";
    }
}
