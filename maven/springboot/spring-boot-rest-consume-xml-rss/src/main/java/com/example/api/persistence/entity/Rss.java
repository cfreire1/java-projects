package com.example.api.persistence.entity;

import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="rss")
public class Rss {

    @XmlElement(name="channel")
    Channel channel;

    @XmlAttribute
    String version;

}