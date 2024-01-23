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
public class Like {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long like_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user_l;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	private Article article_l;
    
	public long getId() {
		return like_id;
	}

	@Override
	public String toString() {
		return "User [id=" + like_id +"]";
	}
}
