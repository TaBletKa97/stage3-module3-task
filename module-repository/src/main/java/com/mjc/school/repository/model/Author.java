package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "author")
@EntityListeners(AuditingEntityListener.class)
public class Author implements BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "author_id_seq",
            sequenceName = "author_id_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "author_id_seq",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "last_update_time")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;
}
