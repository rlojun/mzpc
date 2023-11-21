package com.fivemin.mzpc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController {

    @GetMapping(value = "/time")
    private String time(){
        log.info("test() ==> {}", "test");
        return "members/time/listTime";
    }

    @GetMapping(value = "/qna")
    private String qna(){
        log.info("test() ==> {}", "test");
        return "members/qna";
    }

}
