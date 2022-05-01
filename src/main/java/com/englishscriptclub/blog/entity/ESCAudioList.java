package com.englishscriptclub.blog.entity;

import lombok.Data;
import java.util.Map;

@Data
public class ESCAudioList implements AudioListInterface{
    private String date;
    private String channel;
    private String subChannel;
    private String channelCN;
    private String subChannelCN;

    public static ESCAudioList toESCAudioList(Map<String, String> item) {
        ESCAudioList list = new ESCAudioList();
        list.date = item.getOrDefault("date", "");
        list.channel = item.getOrDefault("channel", "");
        list.subChannel = item.getOrDefault("sub_channel", "");
        if (list.channel.equals("SC")) {
            list.channelCN = "空中英语教室";
        } else if (list.channel.equals("Ivy")) {
            list.channelCN = "常春藤英语";
        }
        return list;
    }
}
