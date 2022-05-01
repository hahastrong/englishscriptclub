package com.englishscriptclub.blog.logic;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChannelMap {
    private final Map<String, String> lists;

    public ChannelMap() {
        lists = new HashMap<>();
        lists.put("SC","空中英语教室");
        lists.put("Ivy", "常春藤英语");
        lists.put("Analytical", "解析英语");
        lists.put("Enjoy", "生活英语");
    }

    public String getChannel(String key) {
        return lists.getOrDefault(key, "");
    }

}
