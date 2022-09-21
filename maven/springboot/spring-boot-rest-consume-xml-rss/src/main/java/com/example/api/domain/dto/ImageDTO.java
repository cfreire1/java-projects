package com.example.api.domain.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ImageDTO {
    String url;
    String title;
    String link;
    String description;
    String width;
    String height;
}
