package com.englishscriptclub.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WXController {
    @GetMapping("/wx")
    public String WCCheckSignature(@RequestParam("signature") String signature,
                                   @RequestParam("timestamp") String timestamp,
                                   @RequestParam("nonce") String nonce,
                                   @RequestParam("echostr") String echostr) {


        return "hello";
    }

    @PostMapping("/wx")
    public String WXProcess(@RequestBody Object ob) {

        return "hello";
    }
}
