package com.enble.springchatws.controller;

import com.enble.springchatws.dto.ChatMessage;
import com.enble.springchatws.dto.ChatRoom;
import com.enble.springchatws.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    // 최종 경로 : /pub/chat/message
    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage);
    }

    @PostMapping("/rooms")
    public ChatRoom createRoom(@RequestParam String roomName) {
        return chatService.createRoom(roomName);
    }

    @GetMapping("/rooms")
    public List<ChatRoom> getAllRooms() {
        return chatService.findAllRoom();
    }
}
