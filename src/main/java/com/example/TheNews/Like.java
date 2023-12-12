package com.example.TheNews;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long like_id;
    
}
