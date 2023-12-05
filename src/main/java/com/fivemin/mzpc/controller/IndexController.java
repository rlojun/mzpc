package com.fivemin.mzpc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        log.info("indexController: index() ==>");
        return "index";
    }

    @GetMapping("/members/{storeName}")
    public String memberIndex(@PathVariable(required = false) String storeName) {
        log.info("IndexController: memberIndex() ==>");

        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();

        if (storeName != null) {
            String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
            log.info("IndexController: Encoded storeName: {} ", encodedStoreName);

            if (!encodedStoreName.equals(httpSession.getAttribute("currentUrl"))) {
                httpSession.setAttribute("currentUrl", encodedStoreName);
                return "redirect:/members/" + encodedStoreName;
            }
        }
        return "index";
    }
}


