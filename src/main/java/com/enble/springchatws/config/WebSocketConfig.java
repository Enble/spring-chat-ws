package com.enble.springchatws.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // handshake를 위한 endpoint 지정
    // cors 설정 포함
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat").setAllowedOrigins("*");
    }

    // 메모리 기반인 SimpleMessageBroker 활성화
    // SimpleMessageBroker는 해당 subscribe하는 클라이언트들에게 메시지를 전달하는 간단한 역할 수행
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // `/sub`가 prefix인 경우 이 메시지 브로커가 경로를 가로채어 처리
        // 구독 요청
        registry.enableSimpleBroker("/sub");

        // `/pub`이 prefix인 경우 클라이언트의 메시지를 Broker에게 전달
        // 메시지 발행 요청
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
