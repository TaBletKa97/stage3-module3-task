package com.mjc.school.service.dto.news;

import com.mjc.school.repository.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTORequest {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private List<Long> tags;

    public NewsDTORequest(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public NewsDTORequest(String title, String content, Long authorId, List<Long> tags) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tags = tags;
    }
}
