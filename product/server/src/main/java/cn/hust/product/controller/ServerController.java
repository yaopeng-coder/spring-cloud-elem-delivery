package cn.hust.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-17 14:27
 **/
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "我是product2";
    }
}
