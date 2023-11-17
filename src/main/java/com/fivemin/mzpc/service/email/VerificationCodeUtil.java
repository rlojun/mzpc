package com.fivemin.mzpc.service.email;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class VerificationCodeUtil {

    private static String ssn;
    private static final Object lock = new Object();

    public static void setSsn(String ssn) {
        VerificationCodeUtil.ssn = ssn;
    }

    public static String getSsn() {
        return ssn;
    }

    // HttpServletRequest의 쿠키와 입력 쿠키값이 일치하는지 비교
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
