package com.englishscriptclub.blog.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "escaudiourl")
@Relation(value = "ESCAudioUrl", collectionRelation = "ESCAudioUrls")
public class ESCAudioUrl extends RepresentationModel<ESCAudioUrl> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String channel;
    private String subChannel;
    private String url;
    private String title;
    private String description;

    public ESCAudioUrl(ESCAudioForm form) {
        date = form.getAudioDate();
        channel = form.getChannel();
        subChannel = form.getSubChannel();
        url = form.getUrl();
    }

    public ESCAudioUrl() {

    }

    public static ESCAudioUrl toESC(ESCAudioForm form) {
        ESCAudioUrl escAudioUrl = new ESCAudioUrl();
        escAudioUrl.setUrl(form.getUrl());
        escAudioUrl.setDate(form.getAudioDate());
        escAudioUrl.setChannel(form.getChannel());
        escAudioUrl.setSubChannel(form.getSubChannel());
        escAudioUrl.setTitle(form.getTitle());
        escAudioUrl.setDescription(form.getDescription());
        return escAudioUrl;
    }
}
