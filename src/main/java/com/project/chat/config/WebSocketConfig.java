package com.project.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
//tells Spring that this app uses sockets to handle real time communication

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //we define an endpoint for websocket connections
        //.setAllowedOrigins - allows req from this url (CORS)
        //.withSockJS() - fallback option for clients that don't support native WebSockets
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:5173")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Start a simple in-memory message broker
        // Any message sent to "/topic/..." will be broadcast to all clients subscribed to that topic
        registry.enableSimpleBroker("/topic");

        // Set the prefix for messages sent from clients to the server
        // If a client sends a message to "/app/xyz", Spring routes it to the @MessageMapping("xyz") method
        registry.setApplicationDestinationPrefixes("/app");

    }
}
