package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.CordDTO;
import com.isa.BloodTransferInstitute.exception.MapException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    RabbitListenerEndpointRegistry registry;

    @PostMapping("/start")
    public String StartDelivery(@RequestBody CordDTO cordDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8085/start";
        try {
            restTemplate.postForObject(url, cordDto, Object.class);
        }catch (Exception e){
            throw new MapException();
        }
        registry.getListenerContainer("foo").start();
        return "";
    }

    @RabbitListener(id = "foo", queues = "test", autoStartup = "false")
    public void consumeMessageFromQueue (String cord){
        System.out.println("Message recieved from queue: " + cord);
        this.simpMessagingTemplate.convertAndSend("/socket-publisher", cord);
    }
}
