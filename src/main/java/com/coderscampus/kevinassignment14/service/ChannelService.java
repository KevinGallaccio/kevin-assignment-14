package com.coderscampus.kevinassignment14.service;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import com.coderscampus.kevinassignment14.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public Channel save(Channel channel) {
        channelRepository.save(channel);
        return channel;
    }

    public Channel findChannelById(Long channelId) {
        return channelRepository.findById(channelId);
    }

    public List<Channel> findAllChannels() {
        return channelRepository.findAll();
    }

    public void deleteChannel(Channel channel) {
        channelRepository.delete(channel);
    }

    public void saveMessageToChannel(Channel channel, Message message) {
        channelRepository.saveMessageToChannel(channel, message);
        channelRepository.save(channel);
    }

}
