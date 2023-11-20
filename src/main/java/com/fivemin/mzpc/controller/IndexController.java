package com.fivemin.mzpc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public String index() {
        log.info("index() ==>");

            return "index";

    }

//    @GetMapping("/")
//    public String index(HttpSession httpSession) {
//        log.info("index() ==>");
//
//        String storedStoreName = (String) httpSession.getAttribute("storeName");
//        if (storedStoreName == null) {
//            return "index";
//        } else {
//            return "/members/index";
//        }
//    }

}
