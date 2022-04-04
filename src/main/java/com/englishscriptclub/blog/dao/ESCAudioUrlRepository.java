package com.englishscriptclub.blog.dao;

import com.englishscriptclub.blog.entity.ESCAudioUrl;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ESCAudioUrlRepository extends JpaRepository<ESCAudioUrl, Long> {
    List<ESCAudioUrl> findByDateEquals(String date);
    ESCAudioUrl findById(int id);
    List<ESCAudioUrl> findAllBySubChannelOrderByIdDesc(String subChannel);
    List<ESCAudioUrl> findAllByChannelAndSubChannelOrderByDateAsc(String Channel, String subChannel);
}
