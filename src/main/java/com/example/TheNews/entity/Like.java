package com.example.TheNews.entity;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConctructor
@Builder
@Entity
@Table(name = "likes")
public class Like {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long like_id;
    
}
