package cn.hust.order.controller;

import cn.hust.order.dto.OrderDTO;
import cn.hust.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-23 21:22
 **/
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
       // String message = "now" + new Date();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("111www");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
