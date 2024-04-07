package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ChannelRepository {
    private List<Channel> channels;
    private AtomicLong idGenerator = new AtomicLong(1);
    private MessageRepository messageRepository;

    public ChannelRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.channels = new ArrayList<>();
    }

    public void save(Channel channel) {
        if (channel.getId() == null) {
            channel.setId(idGenerator.getAndIncrement());
        }
        channels.add(channel);
    }

    public Channel findById(long id) {
        for (Channel channel : channels) {
            if (channel.getId() == id) {
                return channel;
            }
        }
        return null;
    }

    public List<Channel> findAll() {
        return channels;
    }

    public void delete(Channel channel) {
        channels.remove(channel);
    }

    public void saveMessageToChannel(Channel channel, Message message) {
        List<Message> channelMessages = channel.getMessages();
        if (channelMessages == null) {
            channelMessages = new ArrayList<>(); // Initialize the channel messages list
        }
        messageRepository.save(message);
        channelMessages.add(message);
        channel.setMessages(channelMessages);
    }
}
