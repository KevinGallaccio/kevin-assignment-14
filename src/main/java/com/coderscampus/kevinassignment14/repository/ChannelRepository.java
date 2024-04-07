package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ChannelRepository {
    private Map<Long, Channel> channelMap = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);
    private MessageRepository messageRepository;

    public ChannelRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(Channel channel) {
        if (channel.getId() == null) {
            channel.setId(idGenerator.getAndIncrement());
        }
        channelMap.put(channel.getId(), channel);
    }

    public Channel findById(long id) {
        return channelMap.get(id);
    }

    public List<Channel> findAll() {
        return new ArrayList<>(channelMap.values());
    }

    public void delete(Channel channel) {
        channelMap.remove(channel.getId());
    }

    public void saveMessageToChannel(Channel channel, Message message) {
        if (channel.getMessages() == null) {
            channel.setMessages(new ArrayList<>());
        }
        messageRepository.save(message);
        channel.getMessages().add(message);
        save(channel);
    }
}
