package com.example.TheNews.entity;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topics")
public class Topic {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long topic_id;

	@Column(name = "name")
	private String name;
}
