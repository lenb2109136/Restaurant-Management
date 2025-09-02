package com.example.nienluannganh.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class chatController {

    // DTO class để nhận dữ liệu từ client
    public static class ChatMessage {
        private String chat;

        public String getChat() {
            return chat;
        }

        public void setChat(String chat) {
            this.chat = chat;
        }
    }

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public String sendchat(ChatMessage chatMessage) {
        System.out.println("Đã vào server: " + chatMessage.getChat());
        return "server: " + chatMessage.getChat();
    }
}
