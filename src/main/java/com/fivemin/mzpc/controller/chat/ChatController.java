    package com.fivemin.mzpc.controller.chat;


    import com.fivemin.mzpc.data.entity.ChatMessage;
    import com.fivemin.mzpc.data.entity.Members;
    import com.fivemin.mzpc.data.repository.MemberRepository;
    import com.fivemin.mzpc.data.repository.WebSocketEventListener;
    import org.apache.catalina.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.messaging.handler.annotation.DestinationVariable;
    import org.springframework.messaging.handler.annotation.MessageMapping;
    import org.springframework.messaging.handler.annotation.Payload;
    import org.springframework.messaging.handler.annotation.SendTo;
    import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
    import org.springframework.messaging.simp.SimpMessageSendingOperations;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    import javax.servlet.http.HttpSession;
    import java.security.Principal;
    import java.text.SimpleDateFormat;
    import java.time.LocalDateTime;
    import java.util.Date;
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;

    //채팅 메시지를 보내고, 새로운 사용자를 추가하는 등의 채팅 관련 기능을 관리하는 컨트롤러
    // 관리자 고정 ?
    @RestController(value = "newChatController")
    public class ChatController {

        @Autowired
        private MemberRepository memberRepository; // 멤버 정보를 가져오기 위한 레포지토리

        @Autowired
        private WebSocketEventListener webSocketEventListener;

        @Autowired
        private SimpMessageSendingOperations messagingTemplate;

        // 사용자 ID와 웹소켓 세션 ID를 매핑하는 Map
        private final Map<String, String> userSessionMap = new ConcurrentHashMap<>();

        @MessageMapping("/app/chat.sendMessage/admin/{memberId}")
        public void sendMessageToUser(@DestinationVariable String memberId, @Payload ChatMessage chatMessage, HttpSession session) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                chatMessage.setSender(username);
            } else {
                System.err.println("Session does not contain username. The user might not be authenticated.");
            }

            Members member = memberRepository.findByName(chatMessage.getSender());
            chatMessage.setMembers(member);

            // 사용자 ID에 맞는 토픽으로 메시지를 보냅니다.
            messagingTemplate.convertAndSend(String.format("/topic/room123", memberId), chatMessage);
        }

    /*    @MessageMapping("/chat.addUser")
        @SendTo("/topic/room123")
        public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
            // 사용자 이름이 등록되어 있지 않으면, 에러 메시지를 반환
            if (chatMessage.getSender() == null || chatMessage.getSender().isEmpty()) {
                String ADMIN_NAME = "Admin"; // 고정된 관리자 이름
                chatMessage.setSender(ADMIN_NAME);
            }
            if (chatMessage.getSender() == null || chatMessage.getSender().isEmpty()) {
                throw new IllegalArgumentException("사용자 이름이 필요합니다.");
            } else {
                // 사용자를 Map에 추가
                userSessionMap.put(chatMessage.getSender(), headerAccessor.getSessionId());
                headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            }

            // 채팅한 날짜 설정
            chatMessage.setChatDate(LocalDateTime.now());

            // 채팅한 멤버 설정
            Members member = memberRepository.findByName(chatMessage.getSender());
            chatMessage.setMembers(member);

            return chatMessage;
*/

        @MessageMapping("/chat.sendMessage/{roomId}")
        public ChatMessage sendMessage(@DestinationVariable String roomId, ChatMessage chatMessage, Principal principal) {
            if (principal != null) {
                chatMessage.setSender(principal.getName());
            }

            System.out.println("Received a message from " + chatMessage.getSender());
            messagingTemplate.convertAndSend("/topic/" + roomId, chatMessage);
            System.out.println(chatMessage.getContent());

            return chatMessage;
        }

        @MessageMapping("/app/chat.sendMessageToAdmin")
        public void sendMessageToAdmin(@Payload ChatMessage chatMessage) {
            // 받은 메시지의 보낸이를 설정
            Members member = memberRepository.findByName(chatMessage.getSender());
            chatMessage.setMembers(member);

            // 관리자에게 메시지 전송
            messagingTemplate.convertAndSend("/topic/room123", chatMessage);

        }
    }




