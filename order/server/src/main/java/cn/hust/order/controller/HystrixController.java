package cn.hust.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 服务降级
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-25 12:20
 **/
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //设置服务出错回调的函数和超时时间
   /* @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
   @HystrixCommand(commandProperties = {
           @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
           @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
           @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
           @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
   })
    @GetMapping("/getProductInfoList")
    public String  getProductInfoList(@RequestParam("number") Integer number){

       if(number % 2 == 0){
           return "success";
       }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8084/product/listForOrder",
                Arrays.asList("111"),
                String.class);
    }


    public String fallback(){
        return "哎哟喂，太拥挤啦";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤了, 请稍后再试~~";
    }
}
