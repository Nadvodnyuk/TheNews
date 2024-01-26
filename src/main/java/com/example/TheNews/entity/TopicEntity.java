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
public class TopicEntity {
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
	Set<ArticleEntity> articles = new HashSet<>();

	public long getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(long topic_id) {
		this.topic_id = topic_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ArticleEntity> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleEntity> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "User [id=" + topic_id + ", name=" + name +"]";
	}
}
