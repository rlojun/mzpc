package com.fivemin.mzpc.controller.admin;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.service.admin.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
-기능
시간 상품 목록  0
시간 상품 추가  0
시간 상품 상세보기
시간 상품 수정
시간 상품 삭제
 */
@Controller
@Slf4j
@RequestMapping("/admin/{storeCode}/time")
public class AdminTimeController {

    @Autowired
    private TimeService timeService;

    // 시간 상품 목록
    @GetMapping("/listTime")
    public String listTime(@PathVariable String storeCode, Model model){
        log.info("storeCode : {}", storeCode);
        List<TimeDto> listTimes = timeService.listTime(storeCode);
        log.info("listTime : {} : ==>  ", listTimes);
        model.addAttribute("listTime", listTimes);
        return "/admin/time/listTime";
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

//    // 시간 상품 상세보기
//    @GetMapping("/detailTime/{timeId}")
//    public String detailTime(){
//
//        return "admin/time/detailTime";
//    }

    // 해당 시간 상품 수정폼 으로 이동
    @GetMapping("/modifyTime/{timeCode}")
    public String modifyTimeForm(@PathVariable String timeCode, Model model){
        Times times = timeService.DetailTime(timeCode);
        model.addAttribute("times", times);
        return "admin/time/modifyTime";
    }

    // 시간 상품 수정 기능
    @PostMapping("/modifyTime")
    public String modifyTime(){

        return "redirect:/{adminId}/time/detailTime";
    }

    // 시간 상품 삭제 기능
    @GetMapping("/deleteTime")
    public String deleteTime(){

        return "redirect:/{adminId}/time/listTime";
    }
}
