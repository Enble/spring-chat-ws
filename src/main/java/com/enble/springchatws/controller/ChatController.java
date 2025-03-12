package com.enble.springchatws.controller;

import com.enble.springchatws.dto.ChatMessageDto;
import com.enble.springchatws.dto.ChatRoomDto;
import com.enble.springchatws.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/{roomId}/messages")
    public void chat(@PathVariable String roomId, ChatMessageDto chatMessageDto) {
        messagingTemplate.convertAndSend("/sub/" + roomId, chatMessageDto);
    }

    @PostMapping("/rooms")
    public ChatRoomDto createRoom(@RequestParam String roomName) {
        return chatService.createRoom(roomName);
    }

    @GetMapping("/rooms")
    public List<ChatRoomDto> getAllRooms() {
        return chatService.findAllRoom();
    }
}
