package cn.hust.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-23 21:08
 **/
public interface StreamClient {
    String INPUT = "myMessage";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT)
    MessageChannel output();
}
