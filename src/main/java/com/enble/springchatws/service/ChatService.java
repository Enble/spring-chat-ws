package com.enble.springchatws.service;

import com.enble.springchatws.dto.ChatMessage;
import com.enble.springchatws.dto.ChatRoom;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;
    private final SimpMessagingTemplate messagingTemplate;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoom createRoom(String roomName) {
        ChatRoom chatRoom = ChatRoom.builder()
                .id(UUID.randomUUID().toString())
                .name(roomName)
                .build();
        chatRooms.put(roomName, chatRoom);
        return chatRoom;
    }

    public void sendMessage(ChatMessage chatMessage) {
        ChatRoom chatRoom = chatRooms.get(chatMessage.getRoomId());

        messagingTemplate.convertAndSend("/sub/chat/room/" + chatRoom.getId(), chatMessage);
    }
}
