package com.example.TheNews.entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long comment_id;

	@Column(name = "comment_text")
	private String comment_text;

    @Column(name = "comment_date")
	private java.sql.Timestamp comment_date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user_c;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	private ArticleEntity article_c;

	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public Timestamp getComment_date() {
		return comment_date;
	}

	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}

	public UserEntity getUser_c() {
		return user_c;
	}

	public void setUser_c(UserEntity user_c) {
		this.user_c = user_c;
	}

	public ArticleEntity getArticle_c() {
		return article_c;
	}

	public void setArticle_c(ArticleEntity article_c) {
		this.article_c = article_c;
	}

	@Override
	public String toString() {
		return "User [id=" + comment_id + ", date=" + comment_date + "]";
	}
}
