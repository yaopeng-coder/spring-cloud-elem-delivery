package cn.hust.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-22 22:23
 **/
@RestController
@RequestMapping("/env")
public class EnvController {

    @Value("${env}")
    private String env;

    @RequestMapping("/print")
    public String print(){
        return env;
    }
}
