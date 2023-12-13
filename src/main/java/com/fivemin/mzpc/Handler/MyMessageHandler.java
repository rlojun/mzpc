    package com.fivemin.mzpc.Handler;


    import com.fivemin.mzpc.data.entity.ChatMessage;
    import com.fivemin.mzpc.data.entity.Members;
    import org.springframework.stereotype.Component;
    import org.springframework.web.socket.TextMessage;
    import org.springframework.web.socket.WebSocketSession;
    import org.springframework.web.socket.handler.TextWebSocketHandler;


    import javax.servlet.http.HttpSession;
    import java.io.IOException;
    import java.util.HashMap;
    import java.util.Map;

    @Component
    public class MyMessageHandler extends TextWebSocketHandler {

        Map<String, WebSocketSession> userSessionMap = new HashMap<>();

        @Override
        public void afterConnectionEstablished(WebSocketSession session) {
            // 사용자가 웹소켓에 연결되면, 사용자의 아이디와 WebSocketSession을 맵에 저장합니다.
            String userId = getUserId(session);
            userSessionMap.put(userId, session);
        }

        @Override
        public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
            // 메시지를 보낸 사용자의 아이디를 가져옵니다.
            String senderId = getUserId(session);

            // 메시지를 받는 사용자의 아이디를 결정합니다.
            // 이 예시에서는 관리자의 아이디가 'admin'이라고 가정합니다.
            // 하지만 실제로는 메시지에서 받는 사용자의 아이디를 가져와야 할 수도 있습니다.
            String receiverId = "admin";

            // 메시지를 받는 사용자의 WebSocketSession을 맵에서 가져옵니다.
            WebSocketSession receiverSession = userSessionMap.get(receiverId);

            // 메시지를 받는 사용자의 WebSocketSession에 메시지를 전송합니다.
            receiverSession.sendMessage(message);
        }

        private String getUserId(WebSocketSession session) {
            // WebSocketSession에서 사용자의 아이디를 가져오는 코드를 작성합니다.
            // 이 예시에서는 아이디가 세션의 'id' 속성에 저장되어 있다고 가정합니다.
            return (String) session.getAttributes().get("id");
        }
    }
