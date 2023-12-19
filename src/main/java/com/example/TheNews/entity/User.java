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
@Table(name = "users")
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

    @Column(name = "role")
	private String role;

	@Column(name = "username")
	private String username;

    @Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_l")
	private List<Like> likes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_a")
	private List<Article> articles;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_c")
	private List<Comment> comments;
}
