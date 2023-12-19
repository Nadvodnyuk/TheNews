package com.example.TheNews.entity;
import jakarta.persistence.*;
import lombok.*;

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

//	@ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
//	private Topic topics;
}
