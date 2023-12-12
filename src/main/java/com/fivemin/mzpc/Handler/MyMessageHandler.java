    package com.fivemin.mzpc.Handler;


    import com.fivemin.mzpc.data.entity.Members;
    import org.springframework.stereotype.Component;
    import org.springframework.web.socket.TextMessage;
    import org.springframework.web.socket.WebSocketSession;
    import org.springframework.web.socket.handler.TextWebSocketHandler;


    import javax.servlet.http.HttpSession;
    import java.util.Map;

    @Component
    public class MyMessageHandler extends TextWebSocketHandler {

        @Override
        public void handleTextMessage(WebSocketSession session, TextMessage message) {
            // WebSocket 세션에서 HTTP 세션을 가져옵니다.
            Map<String, Object> httpAttributes = session.getAttributes();
            HttpSession httpSession = (HttpSession) httpAttributes.get("SPRING.SESSION.SESSION");

            // 이제 HTTP 세션에서 사용자 정보 등을 가져올 수 있습니다.
            Members member = (Members) httpSession.getAttribute("member");

        }
    }
