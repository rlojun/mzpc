package com.fivemin.mzpc.controller;

import com.fivemin.mzpc.data.dto.AuthDTO;
import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.service.LoginService;
import com.fivemin.mzpc.service.email.EmailService;
import com.fivemin.mzpc.service.email.VerificationCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/*
회원가입 Controller
do -> auth(회원가입), member -> user
 */
@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private EmailService emailService;

    private HttpSession session;

    //로그인 페이지 이동
    @GetMapping(value = "")
    public String loginForm() {
        return "login";
    }

    /*
        로그인 (유저 또는 관리자가 로그인)
        @return
        - 유저면 유저 메인 페이지로 이동
        - 관리자이면 관리자 메인 페이지로 이동
     */
    @PostMapping
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw,
                        @RequestParam(value = "isAdmin", required = false, defaultValue = "false") boolean isAdmin,
                        Model model) {

        String loginType = isAdmin ? "admin" : "members";

        if("admin".equals(loginType)){
            return adminLogin(id, pw, model);
        } else if("members".equals(loginType)){
            return memberLogin(id, pw, session);
        }
        return "redirect:/login?error";
    }

    // 관리자 로그인
    public String adminLogin(@RequestParam("adminId") String adminId
            , @RequestParam("adminPw") String adminPw, Model model){

        Admin admin = loginService.findByAdminId(adminId);

        if(admin != null && admin.getPw().equals(adminPw)){
            session.setAttribute("id", admin.getId());
            session.setAttribute("pw", admin.getPw());

            String adminCode = admin.getCode();
            model.addAttribute("code",adminCode);

            return String.format("redirect:/%s/food/listFood",adminCode);
        }else {
            return "redirect:/login?error";
        }
    }

    // 사용자 로그인
    public String memberLogin(@RequestParam("memberId") String memberId
            , @RequestParam("memberPw") String memberPw, HttpSession session){

        Members members = loginService.findByMemberId(memberId);

        if(members != null && members.getPw().equals(memberPw)){
            session.setAttribute("id", members.getId());
            session.setAttribute("pw", members.getPw());

            String storeName = members.getStore().getName();
            String encodedStoreName = URLEncoder.encode(storeName, StandardCharsets.UTF_8);
            return String.format("redirect:/%s/food/listFood",encodedStoreName);
        }else{
            return "redirect:/login?error";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login?logout";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/auth")
    public String authForm(@ModelAttribute("authDTO")AuthDTO authDTO){
        return "members/authUser";
    }


    // 회원 가입 로직 구현
    @PostMapping("/auth")
    public String auth(@Validated @ModelAttribute("authDTO") AuthDTO authDTO,
                       RedirectAttributes redirectAttributes, BindingResult result){
        // 유효성 검사
        if (result.hasErrors()){
            return "members/authUser";
        }
        loginService.auth(authDTO);
        return "redirect:/login";
    }

    //아이디 찾기 페이지로 이동
    @GetMapping("/findId")
    public String findIdForm(){

        return "members/find/findId";
    }

    // 아이디를 찾아주는 기능
    @PostMapping("/findId")
    public String findId(@RequestParam String name, @RequestParam String ssn, Model model){
        Members members = loginService.findId(name, ssn);

        if (members != null) {
            model.addAttribute("result", "사용자 ID: " + members.getId());
        } else {
            model.addAttribute("result", "정보와 일치하는 유저가 없습니다.");
        }
        return "members/find/findId";
    }

    //비밀번호 찾기 페이지로 이동
    @GetMapping("/findPw")
    public String findPwForm(){

        return "members/find/findPw";
    }

    //비밀번호를 찾아주는 기능
    @PostMapping("/findPw")
    public String findPw(@RequestParam String name, @RequestParam String ssn,
                         @RequestParam String email, Model model, HttpServletResponse response) throws Exception {
        Members members = loginService.findPw(name, ssn, email);

        if (members != null){
            // 확인코드
            String verificationCode = emailService.sendSimpleMessage(email);

            // 생성된 인증 코드를 쿠키에 추가
            Cookie verificationCodeCookie = new Cookie("verificationCode", verificationCode);
            response.addCookie(verificationCodeCookie);

            // 찾은 사용자의 SSN을 저장
            VerificationCodeUtil.setSsn(ssn);

            model.addAttribute("result", "이메일이 전송되었습니다.");
        } else{
            model.addAttribute("result", "정보와 일치하는 유저가 없습니다.");
        }

        return "members/find/findPw";
    }


    // 인증번호 확정
    @PostMapping("/verifyCode")
    public String verifyCode(@RequestParam String memberInput, HttpServletRequest request, Model model) {
        boolean isCodeValid = VerificationCodeUtil.isVerificationCodeValid(request, memberInput);

        if (isCodeValid) {
            // 입력값과 쿠키에 담긴 verificationCode가 일치하는 경우
            model.addAttribute("result2", "인증 성공!");
            System.out.println("인증 성공");
            return "redirect:/login/resetPw";
        } else {
            // 일치하지 않는 경우
            model.addAttribute("result2", "인증 실패. 다시 시도하세요.");
        }

        return "members/find/findPw";
    }

    // 비밀번호 재설정 페이지 이동
    @GetMapping("/resetPw")
    public String resetPwForm(Model model){
        // VerificationCodeUtil을 통해 저장된 아이디를 가져온다
        String ssn = VerificationCodeUtil.getSsn();
        // id를 사용하여 데이터베이스에서 사용자 정보를 조회한다.
        Optional<Members> membersOptional = loginService.findBySsn(ssn);
        System.out.println("페이지 이동");
        // 조회된 사용자 정보를 모델에 추가하여 뷰로 전달한다.
        membersOptional.ifPresent(members -> model.addAttribute("members", members));
        return "members/find/resetPw";
    }

    // 비밀번호 재설정 로직
    @PostMapping("/resetPw")
    public String resetPw(@RequestParam String pw){
        String ssn = VerificationCodeUtil.getSsn();
        VerificationCodeUtil.setSsn(null);
        loginService.updatePw(ssn, pw);
        System.out.println("재설정 로직" + pw + ssn);
        return "redirect:/login";
    }

    //이메일 인증 테스트 폼
    @GetMapping("/emailTestForm")
    public String emailTestForm() {
        return "members/find/emailTest";
    }

    // 이메일 인증
    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendSimpleMessage(email);
        return confirm;
    }
}
