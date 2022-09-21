package com.example.api.persistence.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="item")
public class Item{
    String title;
    String description;
    String link;
    String category;
    String comments;
    String pubDate;
}