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
    
}
