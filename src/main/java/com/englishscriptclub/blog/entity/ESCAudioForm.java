package com.englishscriptclub.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ESCAudioForm {
    @JsonProperty("audio_date")
    private String audioDate;
    private String channel;
    @JsonProperty("sub_channel")
    private String subChannel;
    private String url;
    private String title;
    private String description;
}
