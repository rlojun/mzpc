package com.fivemin.mzpc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public String index() {
        log.info("index() ==>");
        return "index";

    }

}
