package com.example.TheNews.entity;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConctructor
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
