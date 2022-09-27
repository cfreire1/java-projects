package com.example.api.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RssDTO {
    ChannelDTO channel;
    String version;
}