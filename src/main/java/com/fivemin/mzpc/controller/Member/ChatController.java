package com.fivemin.mzpc.controller.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
-기능
사용자입장에서 관리자와의 채팅 문의 기능
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

//    private final ChatService service;
//
//    @PostMapping
//    public ChatRoom createRoom(@RequestParam String name){
//        return service.createRoom(name);
//    }
//
//    @GetMapping
//    public List<ChatRoom> findAllRooms(){
//        return service.findAllRoom();
//    }
}
