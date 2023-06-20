package com.mjc.school.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tag")
public class Tag implements BaseEntity<Long> {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "tag_id_seq",
            sequenceName = "tag_id_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "tag_id_seq",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "news_tag",
            joinColumns = @JoinColumn(name = "tags_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    @ToString.Exclude
    private List<News> news;

    public void addNews(News news) {
        if (this.news == null) {
            this.news = new ArrayList<>();
        }
        this.news.add(news);
    }
}
