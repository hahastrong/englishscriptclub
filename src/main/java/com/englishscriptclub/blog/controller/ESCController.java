package com.englishscriptclub.blog.controller;

import com.englishscriptclub.blog.dao.ESCAudioUrlRepository;
import com.englishscriptclub.blog.entity.ESCAudioUrl;
import com.englishscriptclub.blog.logic.WXLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/englishscriptclub")
public class ESCController {
    @Autowired
    private ESCAudioUrlRepository escAudioUrlRepository;

    @Value("${ua.wechat}")
    private String uaType;


    @RequestMapping ("/audio")
    public String queryAudioInfos(@RequestParam(name = "date", defaultValue = "") String date,
                                  @RequestParam(name = "channel", defaultValue = "") String channel,
                                  @RequestParam(name = "sub_channel", defaultValue = "") String subChannel,
                                  HttpServletRequest request,
                                  Model model) {

        String ua = request.getHeader("User-Agent");
        if (!ua.contains(uaType)) {
            return "onlyOpenInWx";
        }
        List<ESCAudioUrl> audios;
        if (date.isEmpty()) {
//            SimpleDateFormat sdf = new SimpleDateFormat();
//            sdf.applyPattern("yyyyMMdd");
//            date = sdf.format(new Date());
            if (channel.isEmpty()) {
                channel = "SC";
            }
            if (subChannel.isEmpty()) {
                subChannel = "AD";
            }
            audios = escAudioUrlRepository.findAllByChannelAndSubChannelOrderByDateAsc(channel, subChannel);

        } else {
            // 后续支持按日期查询
            audios = escAudioUrlRepository.findAllByChannelAndSubChannelOrderByDateAsc(channel, subChannel);
        }

        model.addAttribute("audios", audios);
        return "audios";
    }
}
