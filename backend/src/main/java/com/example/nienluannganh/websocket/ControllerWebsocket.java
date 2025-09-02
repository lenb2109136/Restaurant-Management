package com.example.nienluannganh.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class ControllerWebsocket implements WebSocketHandler {
	private static HashMap<String , WebSocketSession> ds= new HashMap<String, WebSocketSession>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Khi kết nối WebSocket được thiết lập thành công
        System.out.println("New WebSocket connection established: " + session.getId());
        ds.put(session.getId(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Khi nhận được tin nhắn từ client
        String payload = (String) message.getPayload();
        System.out.println("Received message: " + payload);
        
        // Gửi lại tin nhắn cho tất cả các client trong ds
        for (WebSocketSession s : ds.values()) {
            s.sendMessage(new TextMessage("Server received: " + payload));
        }
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Xử lý lỗi khi truyền tải
        System.out.println("Error occurred: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // In ra ID của WebSocket session
        System.out.println("Connection closed: " + session.getId());

        // In ra trạng thái đóng kết nối (status)
        System.out.println("Close Status: " + status);
        
        // Nếu bạn muốn thêm thông tin về lý do kết nối bị đóng (nếu có)
        if (status.getCode() != CloseStatus.NORMAL.getCode()) {
            System.out.println("Connection closed with error code: " + status.getCode());
        }
    }


    @Override
    public boolean supportsPartialMessages() {
        // Chỉ hỗ trợ tin nhắn đầy đủ, không hỗ trợ phân mảnh
        return false;
    }
}
