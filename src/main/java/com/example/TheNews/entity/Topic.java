package com.example.TheNews.entity;

import java.util.HashSet;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

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

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="article_topic",
			joinColumns={ @JoinColumn(name="topicId", referencedColumnName="topic_id",
					nullable = false, updatable = false)},
			inverseJoinColumns={ @JoinColumn(name="articleId", referencedColumnName="article_id",
					nullable = false, updatable = false)})
	@Builder.Default 
	Set<Article> articles = new HashSet<>();

	public long getId() {
		return topic_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + topic_id + ", name=" + name +"]";
	}
}
