package com.fivemin.mzpc.controller;

import com.fivemin.mzpc.data.dto.AuthDTO;
import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/*
회원가입 Controller
do -> auth(회원가입), member -> user
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

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
                        HttpSession session) {

        String loginType = isAdmin ? "admin" : "members";

        if("admin".equals(loginType)){
            return adminLogin(id, pw, session);
        } else if("members".equals(loginType)){
            return memberLogin(id, pw, session);
        }
        return "redirect:/login?error";
    }

    // 관리자 로그인
    public String adminLogin(@RequestParam("adminId") String adminId
            , @RequestParam("adminPw") String adminPw, HttpSession session){

        Admin admin = loginService.findByAdminId(adminId);

        if(admin != null && admin.getPw().equals(adminPw)){
            session.setAttribute("admin", admin);
            return "redirect:/login/admin/food/listFood";
        }else{
            return "redirect:/login?error";
        }
        // return "redirect:/food/{adminId}";
    }

    // 사용자 로그인
    public String memberLogin(@RequestParam("memberId") String memberId
            , @RequestParam("memberPw") String memberPw, HttpSession session){

        Members members = loginService.findByMemberId(memberId);

        if(members != null && members.getPw().equals(memberPw)){
            session.setAttribute("members", members);
            return "redirect:/login/members/food/listFood";
        }else{
            return "redirect:/login?error";
        }
        // return "redirect:/food/{memberId}";
    }

    // admin 확인 후 페이지 이동
    @GetMapping("/admin/food/listFood")
    public String adminFoodMenu(HttpSession session){
        return checks(session, Admin.class, "admin/food/listFood");
    }

    // member 확인 후 페이지 이동
    @GetMapping("/members/food/listFood")
    public String memberFoodMenu(HttpSession session){
        return checks(session, Members.class, "members/food/listFood");
    }

    // 로그인시 리다이렉트 할 페이지를 결정
    private String checks(HttpSession session, Class<?> userType, String redirectPath){
        Object user = session.getAttribute(userType.getSimpleName().toLowerCase());

        if (userType.isInstance(user)){
            return redirectPath;
        }else {
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

        // 이부분 지금 안된다. 수정예정
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.");

        return "redirect:/login";
    }

    //아이디 찾기 페이지로 이동
    @GetMapping("/findId")
    public String findIdForm(){

        return "members/find/findId";
    }

    // 아이디를 찾아주는 기능
    @PostMapping("/findId")
    public String findId(){

        return "redirect:/login";
    }

    //비밀번호 찾기 페이지로 이동
    @GetMapping("/findPw")
    public String findPwForm(){

        return "members/find/findPw";
    }

    //비밀번호를 찾아주는 기능
    @PostMapping("/findPw")
    public String findPw(){

        return "redirect:/login";
    }
}
