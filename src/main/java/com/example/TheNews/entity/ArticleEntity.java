package com.example.TheNews.entity;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long article_id;

	@Column(name = "title")
	private String title;

	@Column(name = "article_text")
	private String article_text;

    @Column(name = "image_url")
	private String image_url;

	@Column(name = "like_num")
	private Integer like_num;

    @Column(name = "comment_num")
	private Integer comment_num;

    @Column(name = "publication_date")
	private java.sql.Timestamp publication_date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user_a;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article_l")
	private List<LikeEntity> likes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article_c")
	private List<CommentEntity> comments;

	@ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
	@Builder.Default 
	private Set<TopicEntity> topics = new HashSet<>();

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
		return publication_date;
	}

	public void setPublication_date(Timestamp publication_date) {
		this.publication_date = publication_date;
	}

	public UserEntity getUser_a() {
		return user_a;
	}

	public void setUser_a(UserEntity user_a) {
		this.user_a = user_a;
	}

	public List<LikeEntity> getLikes() {
		return likes != null ? likes : Collections.emptyList();
	}

	public void setLikes(List<LikeEntity> likes) {
		this.likes = likes;
	}

	public List<CommentEntity> getComments() {
		return comments != null ? comments : Collections.emptyList();
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public Set<TopicEntity> getTopics() {
		return topics;
	}

	public void setTopics(Set<TopicEntity> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "User [id=" + article_id + ", title=" + title + 
		", article text=" + article_text + ", image=" + image_url + 
		", number of likes=" + like_num +", number of comments=" + comment_num +
		", publication date=" + publication_date +"]";
	}
}
