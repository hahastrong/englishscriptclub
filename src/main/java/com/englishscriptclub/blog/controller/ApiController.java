package com.englishscriptclub.blog.controller;

import com.englishscriptclub.blog.dao.ESCAudioUrlRepository;
import com.englishscriptclub.blog.entity.ESCAudioForm;
import com.englishscriptclub.blog.entity.ESCAudioUrl;
import com.englishscriptclub.blog.logic.RestLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequestMapping(value = "englishscriptclub/api", produces = {"application/json"})
public class ApiController {
    @Autowired
    private ESCAudioUrlRepository escAudioUrlRepository;

    @Autowired
    private RestLogic restLogic;

    @PostMapping("/audio")
    public String uploadAudioUrl(@RequestBody ESCAudioForm form) {
        if (form.getUrl().equals("")) {
            return "failure";
        }
        ESCAudioUrl esc = ESCAudioUrl.toESC(form);
        esc = escAudioUrlRepository.save(esc);
        log.info("id: {}", esc.getId());
        if (esc.getId() <= 0) {
            return "failure";
        }
        return esc.toString();
    }

    @GetMapping("/audio")
    public ResponseEntity<List<ESCAudioUrl>> queryAudio() {
        Pageable page = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<ESCAudioUrl> list = escAudioUrlRepository.findAll(page);
        if (list.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list.getContent(), HttpStatus.OK);
    }

    @GetMapping("/audio/{id}")
    public ResponseEntity<ESCAudioUrl> queryAudioById(@PathVariable String id) {
        ESCAudioUrl audio = escAudioUrlRepository.findById(Integer.parseInt(id));
        return new ResponseEntity<>(audio, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testGet() {
        int id = 1;
        restLogic.getAudiosById(id);
        restLogic.getAudioByIdByUrl(id);
        restLogic.getAudioByIdByMap(id);
        restLogic.getAudioByIdUsingEntity(id);
        return "success";
    }

    @GetMapping("/resource/audio")
    public CollectionModel<ESCAudioUrl> queryResource() {
        Pageable page = PageRequest.of(0, 10, Sort.by("id").descending());
        List<ESCAudioUrl> list = escAudioUrlRepository.findAll(page).getContent();
        for (ESCAudioUrl item : list) {
            Link selfLink = linkTo(methodOn(ApiController.class).queryResource()).slash(item.getId()).withSelfRel();
            item.add(selfLink);
        }
        Link link = linkTo(methodOn(ApiController.class).queryResource()).withSelfRel();
        CollectionModel<ESCAudioUrl> result = CollectionModel.of(list, link);

        return result;
    }

}
