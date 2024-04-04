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
	@JoinColumn(name = "user_id")
	private long user_l;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	private long article_l;

	public long getLike_id() {
		return like_id;
	}

	public void setLike_id(long like_id) {
		this.like_id = like_id;
	}

	public long getUser_l() {
		return user_l;
	}

	public void setUser_l(long user_l) {
		this.user_l = user_l;
	}

	public long getArticle_l() {
		return article_l;
	}

	public void setArticle_l(long article_l) {
		this.article_l = article_l;
	}

	@Override
	public String toString() {
		return "User [id=" + like_id + "]";
	}
}
