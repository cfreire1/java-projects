package com.example.api.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ItemDTO {
    String title;
    String description;
    String link;
    String category;
    String comments;
    String pubDate;
}
