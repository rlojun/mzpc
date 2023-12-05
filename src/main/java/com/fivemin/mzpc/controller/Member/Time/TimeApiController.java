package com.fivemin.mzpc.controller.Member.Time;

import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.Map;

@RestController
@Slf4j
public class TimeApiController {

    @Autowired
    LoginService loginService;

    @GetMapping("/api/updateRemainingTime")
    public ResponseEntity<?> updateRemainingTime(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("id");

        log.info("sessionId : {} : ", sessionId);

        if (sessionId == null) {
            return ResponseEntity.badRequest().body("Invalid session");
        }

        Members loginUser = loginService.findByMemberId(sessionId);

        if (loginUser == null) {
            return ResponseEntity.badRequest().body("Invalid memberId");
        }

        LocalTime remainingTime = loginUser.getRemainingTime();
        log.info("remainingTime : {} : ", remainingTime);

        // LocalTime을 문자열로 변환하여 JSON 형식으로 응답
        return ResponseEntity.ok().body(
                Map.of("remainingTime", remainingTime != null ? remainingTime.toString() : "")
        );
    }
}
