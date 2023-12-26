package com.example.TheNews.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article {
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
	private User user_a;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article_l")
	private List<Like> likes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article_c")
	private List<Comment> comments;

	@ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
	@Builder.Default 
	private Set<Topic> topics = new HashSet<>();

	public long getId() {
		return article_id;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return article_text;
	}

	public String getImage() {
		return image_url;
	}

	public Integer getLikeNum() {
		return like_num;
	}

	public Integer getCommentNum() {
		return comment_num;
	}

	public java.sql.Timestamp getDate() {
		return publication_date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String article_text) {
		this.article_text = article_text;
	}

	public void setImage(String image_url) {
		this.image_url = image_url;
	}

	public void setLikeNum(Integer like_num) {
		this.like_num = like_num;
	}

	public void setPassword(Integer comment_num) {
		this.comment_num = comment_num;
	}

	public void setTimestamp(java.sql.Timestamp publication_date) {
		this.publication_date = publication_date;
	}

	@Override
	public String toString() {
		return "User [id=" + article_id + ", title=" + title + 
		", article text=" + article_text + ", image=" + image_url + 
		", number of likes=" + like_num +", number of comments=" + comment_num +
		", publication date=" + publication_date +"]";
	}
}
