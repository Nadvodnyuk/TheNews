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

//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinTable(name="article_topic",
//			joinColumns={ @JoinColumn(name="articleId", referencedColumnName="article_id",
//					nullable = false, updatable = false)},
//			inverseJoinColumns={ @JoinColumn(name="topicId", referencedColumnName="topic_id",
//					nullable = false, updatable = false)})
//	private Article articles;
}
