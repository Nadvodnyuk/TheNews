package com.example.TheNews.entity;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConctructor
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
}
