package cn.hust.order.message;

import cn.hust.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-23 21:12
 **/
@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamReceiver {



 /*   @StreamListener(StreamClient.INPUT)
    public void process(Object message){
        log.info("StreamReceiver:{}",message);
    }*/


    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO message){
        log.info("StreamReceiver:{}",message);
        return "received.";
    }

    @StreamListener(StreamClient.INPUT2)
    public void process(String message){
        log.info("StreamReceiver2:{}",message);
    }

}
