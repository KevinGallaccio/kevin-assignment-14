package com.coderscampus.kevinassignment14.web;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.service.ChannelService;
import com.coderscampus.kevinassignment14.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/welcome")
    public String getWelcome(ModelMap model) {
        List<Channel> storedChannels = channelService.findAllChannels();
        if (storedChannels.isEmpty()) {
            Channel generalChannel = new Channel("General");
            channelService.save(generalChannel);
        }
        List<Channel> channels = channelService.findAllChannels();
        model.put("channels", channels);
        return "welcome";
    }

    @GetMapping("/channel/{channelId}")
    public String getChannel(ModelMap model, @PathVariable Long channelId) {
        Channel channel = channelService.findChannelById(channelId);
        model.put("channel", channel);
        return "channel";
    }
}
