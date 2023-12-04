package com.fivemin.mzpc.utility;

import com.fivemin.mzpc.service.member.CartService;
import com.fivemin.mzpc.service.member.FoodService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@WebListener
public class SessionHandler implements HttpSessionListener {

    private final CartService cartService;

    public SessionHandler(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String memberId = (String) se.getSession().getAttribute("id");
        log.info("sessionDestroyed ID : {}", memberId);
        if (memberId != null) {
            cartService.clearCart(memberId);
        }
    }
}
