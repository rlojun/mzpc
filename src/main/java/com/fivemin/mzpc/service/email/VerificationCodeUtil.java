package com.fivemin.mzpc.service.email;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class VerificationCodeUtil {

    public static boolean isVerificationCodeValid(HttpServletRequest request, String userInput) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("verificationCode".equals(cookie.getName())) {
                    String storedVerificationCode = cookie.getValue();
                    return storedVerificationCode.equals(userInput);
                }
            }
        }

        return false;
    }
}
