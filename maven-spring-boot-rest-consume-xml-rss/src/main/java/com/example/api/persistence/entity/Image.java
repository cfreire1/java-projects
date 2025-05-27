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
@XmlType(name="image")
public class Image {
    String url;
    String title;
    String link;
    String description;
    String width;
    String height;
}