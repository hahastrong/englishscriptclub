package com.englishscriptclub.blog.controller;

import com.englishscriptclub.blog.dao.ESCAudioUrlRepository;
import com.englishscriptclub.blog.entity.ESCAudioList;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                                  @RequestParam(name = "subChannel", defaultValue = "") String subChannel,
                                  HttpServletRequest request,
                                  Model model)  {

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
            String startTime = String.format("%s-01", date);

            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd");
            String endTime = "2030-04-30";
            try {
                Date endDate = sdf.parse(startTime);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.MONTH, 1);
                endTime = String.format("%d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                System.out.println(endTime);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            audios = escAudioUrlRepository.findAllByChannelAndDate(startTime, endTime, channel, subChannel);
        }

        model.addAttribute("audios", audios);
        return "audios";
    }

    @RequestMapping("/audioList")
    public String queryAudioList(HttpServletRequest request, Model model) {
        String ua = request.getHeader("User-Agent");
        if (!ua.contains(uaType)) {
            return "onlyOpenInWx";
        }
        List<Map<String, String>> list = escAudioUrlRepository.findByGroupByDateAndChannel();
        List<ESCAudioList> audioList = new ArrayList<>();
        for (Map<String, String> item : list) {
            audioList.add(ESCAudioList.toESCAudioList(item));
        }
        model.addAttribute("lists", audioList);
        System.out.println(audioList);
        return "audioList";
    }
}
