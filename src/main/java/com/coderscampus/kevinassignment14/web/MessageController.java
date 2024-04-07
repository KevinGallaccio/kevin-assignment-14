package com.coderscampus.kevinassignment14.web;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import com.coderscampus.kevinassignment14.service.ChannelService;
import com.coderscampus.kevinassignment14.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final ChannelService channelService;

    public MessageController(MessageService messageService, ChannelService channelService) {
        this.channelService = channelService;
        this.messageService = messageService;
    }

    @PostMapping("/channel/{channelId}")
    public ResponseEntity<String> postMessage(@PathVariable Long channelId, @RequestBody Message message) {
        Channel channel = channelService.findChannelById(channelId);
        channelService.saveMessageToChannel(channel, message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/channel/{channelId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long channelId) {
        List<Message> messages = messageService.findMessagesByChannelId(channelId);
        return ResponseEntity.ok(messages);
    }
}
