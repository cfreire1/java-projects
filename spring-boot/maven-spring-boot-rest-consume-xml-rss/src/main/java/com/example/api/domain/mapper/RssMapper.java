package com.example.api.domain.mapper;

import com.example.api.domain.dto.ChannelDTO;
import com.example.api.domain.dto.ImageDTO;
import com.example.api.domain.dto.ItemDTO;
import com.example.api.domain.dto.RssDTO;
import com.example.api.persistence.entity.Item;
import com.example.api.persistence.entity.Rss;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RssMapper {
    public RssDTO toMapperRss(Rss rss) {
        return RssDTO.builder()
                .channel(ChannelDTO.builder()
                        .title(rss.getChannel().getTitle())
                        .description(rss.getChannel().getDescription())
                        .link(rss.getChannel().getLink())
                        .category(rss.getChannel().getCategory())
                        .copyright(rss.getChannel().getCopyright())
                        .docs(rss.getChannel().getDocs())
                        .language(rss.getChannel().getLanguage())
                        .lastBuildDate(rss.getChannel().getLastBuildDate())
                        .managingEditor(rss.getChannel().getManagingEditor())
                        .pubDate(rss.getChannel().getPubDate())
                        .webMaster(rss.getChannel().getWebMaster())
                        .generator(rss.getChannel().getGenerator())
                        .img(ImageDTO.builder()//OPEN
                                .url(rss.getChannel().getImg().getUrl())
                                .title(rss.getChannel().getImg().getTitle())
                                .link(rss.getChannel().getImg().getLink())
                                .description(rss.getChannel().getImg().getDescription())
                                .width(rss.getChannel().getImg().getWidth())
                                .height(rss.getChannel().getImg().getHeight())
                                .build())
                        .items(this.toMapperItem(rss.getChannel().getItems()))
                        .build())
                .version(rss.getVersion())
                .build();
    }

    public ArrayList<ItemDTO> toMapperItem(List<Item> itemList) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        itemList.stream().forEach(item -> {
            itemDTOList.add(ItemDTO.builder()//OPEN
                    .title(item.getTitle())
                    .description(item.getDescription())
                    .link(item.getLink())
                    .category(item.getCategory())
                    .comments(item.getComments())
                    .pubDate(item.getPubDate())
                    .build());
        });

        return (ArrayList<ItemDTO>) itemDTOList;
    }

}