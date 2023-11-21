package com.fivemin.mzpc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
-기능
시간 상품 목록  0
시간 상품 추가  0
시간 상품 상세보기
시간 상품 수정
시간 상품 삭제
 */
@Controller
@RequestMapping("admin/{adminCode}/time")
public class AdminTimeController {

    // 시간 상품 목록
    @GetMapping("/listTime")
    public String listTime(){

        return "admin/time/listTime";
    }

    // 시간 상품 등록 폼 이동
    @GetMapping("/addTime")
    public String addTimeForm(){

        return "admin/time/addTime";
    }

    // 시간 상품 등록 기능
    @PostMapping("/addTime")
    public String addTime(){

        return "redirect:/{adminId}/time/listTime";
    }

    // 시간 상품 상세보기
    @GetMapping("/detailTime/{timeId}")
    public String detailTime(){

        return "admin/time/detailTime";
    }

    // 해당 시간 상품 수정폼 으로 이동
    @GetMapping("/modifyTime/{timeId}")
    public String modifyTimeForm(){

        return "admin/time/modifyTime";
    }

    // 시간 상품 수정 기능
    @PostMapping("/modifyTime")
    public String modifyTime(){

        return "redirect:/{adminId}/time/detailTime";
            // + times.getId();
    }

    // 시간 상품 삭제 기능
    @GetMapping("/deleteTime")
    public String deleteTime(){

        return "redirect:/{adminId}/time/listTime";
    }

}
