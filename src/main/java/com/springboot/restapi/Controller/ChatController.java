package com.springboot.restapi.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

import com.springboot.restapi.Entity.ChatMessage;
import com.springboot.restapi.Repository.ChatMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private MessageChannel outboundChannel;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(message);
        outboundChannel.send(MessageBuilder.withPayload(message.toString()).build());
    }

    @GetMapping("/messages")
    public List<ChatMessage> getMessages() {
        return chatMessageRepository.findAllByOrderByTimestampAsc();
    }
}
