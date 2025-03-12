package com.enble.springchatws.service;

import com.enble.springchatws.dto.ChatMessageDto;
import com.enble.springchatws.dto.ChatRoomDto;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoomDto> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findRoomById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoomDto createRoom(String roomName) {
        ChatRoomDto chatRoomDto = ChatRoomDto.builder()
                .id(UUID.randomUUID().toString())
                .name(roomName)
                .build();
        chatRooms.put(roomName, chatRoomDto);
        return chatRoomDto;
    }
}
