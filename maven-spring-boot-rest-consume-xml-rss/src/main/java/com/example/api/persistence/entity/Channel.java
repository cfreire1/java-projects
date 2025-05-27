package com.example.api.persistence.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="channel")
public class Channel {
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

    @XmlElement(name="image")
    Image img;

    @XmlElement(name="item")
    ArrayList<Item> items;
}
