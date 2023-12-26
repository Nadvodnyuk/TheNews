package com.example.TheNews.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user_c;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	private Article article_c;

	public long getId() {
		return comment_id;
	}

	public java.sql.Timestamp getTimestamp() {
		return comment_date;
	}

	public void setTimestamp(java.sql.Timestamp comment_date) {
		this.comment_date = comment_date;
	}

	@Override
	public String toString() {
		return "User [id=" + comment_id + ", date=" + comment_date + "]";
	}
}
