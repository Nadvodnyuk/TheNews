package com.example.TheNews;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long comment_id;

	@Column(name = "comment_text")
	private String comment_text;

    @Column(name = "comment_date")
	private java.sql.Timestamp comment_date;
}
