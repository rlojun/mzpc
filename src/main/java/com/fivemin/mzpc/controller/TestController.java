package com.fivemin.mzpc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController {

//    @GetMapping(value = "/food")
//    private String food(){
//        log.info("test() ==> {}", "test");
//        return "members/food/listFood";
//    }

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

    @GetMapping(value = "")
    private String index(){
        log.info("test() ==> {}", "test");
        return "index";
    }


    @GetMapping(value = "/admin/food")
    private String adminFood(){
        log.info("test() ==> {}", "test");
        return "admin/food/listFood";
    }

    @GetMapping(value = "/admin/time")
    private String adminTime(){
        log.info("test() ==> {}", "test");
        return "admin/time/listTime";
    }

    @GetMapping(value = "/admin/order")
    private String order(){
        log.info("test() ==> {}", "test");
        return "admin/listOrder";
    }
    @GetMapping(value = "/admin/member")
    private String member(){
        log.info("test() ==> {}", "test");
        return "admin/listMember";
    }
}
