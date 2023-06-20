package com.mjc.school.service.dto.tag;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class TagDTOResponse {
    private Long id;
    private String name;
}
