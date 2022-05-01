package com.englishscriptclub.blog.dao;

import com.englishscriptclub.blog.entity.ESCAudioUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ESCAudioUrlRepository extends JpaRepository<ESCAudioUrl, Long> {
    List<ESCAudioUrl> findByDateEquals(String date);
    ESCAudioUrl findById(int id);
    List<ESCAudioUrl> findAllBySubChannelOrderByIdDesc(String subChannel);
    List<ESCAudioUrl> findAllByChannelAndSubChannelOrderByDateAsc(String Channel, String subChannel);

   @Query("select u from ESCAudioUrl u where u.date > ?1 and u.date < ?2 and u.channel = ?3 and u.subChannel = ?4")
    List<ESCAudioUrl> findAllByChannelAndDate(String date, String endDate, String channel, String subChannel);

    @Query(value = "select DATE_FORMAT(u.date, '%Y-%m') AS date , u.channel, u.sub_channel from ESCAudioUrl u where u.id > 0 group by DATE_FORMAT(u.date, '%Y-%m'), u.channel,u.sub_channel",
            nativeQuery = true)
    List<Map<String, String>> findByGroupByDateAndChannel();
}
