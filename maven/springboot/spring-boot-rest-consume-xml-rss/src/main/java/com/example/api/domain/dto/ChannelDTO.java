package com.example.api.domain.dto;

import lombok.*;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChannelDTO {
    String title;
    String description;
    String link;
    String category;
    String copyright;
    String docs;
    String language;
    String lastBuildDate;
    String managingEditor;
    String pubDate;
    String webMaster;
    String generator;
    ImageDTO img;
    ArrayList<ItemDTO> items;
}
