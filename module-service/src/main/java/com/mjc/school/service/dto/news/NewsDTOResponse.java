package com.mjc.school.service.dto.news;

import com.mjc.school.repository.model.Tag;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTOResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
    private String authorName;
    private LocalDateTime authorCreateTime;
    private LocalDateTime authorLastUpdateTime;
    private List<Tag> tags;

    @Override
    public String toString() {
        return "NewsDTOResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorCreateTime=" + authorCreateTime +
                ", authorLastUpdateTime=" + authorLastUpdateTime +
                ", tags=" + tags.stream().map(Objects::toString).collect(Collectors.joining(";", "[", "]")) +
                '}';
    }
}
