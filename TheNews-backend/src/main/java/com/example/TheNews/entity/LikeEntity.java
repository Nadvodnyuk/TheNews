package com.example.TheNews.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "likes")
public class LikeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long like_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private UserEntity userL;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleId")
	private ArticleEntity articleL;

	public long getLike_id() {
		return like_id;
	}

	public void setLike_id(long like_id) {
		this.like_id = like_id;
	}

	public UserEntity getUserL() {
		return userL;
	}

	public void setUserL(UserEntity userL) {
		this.userL = userL;
	}

	public ArticleEntity getArticleL() {
		return articleL;
	}

	public void setArticleL(ArticleEntity articleL) {
		this.articleL = articleL;
	}

	@Override
	public String toString() {
		return "User [id=" + like_id + "]";
	}
}
