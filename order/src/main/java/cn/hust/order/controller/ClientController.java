package cn.hust.order.controller;

import cn.hust.order.client.ProductClient;
import cn.hust.order.dataobject.ProductInfo;
import cn.hust.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-17 14:30
 **/
@RestController
@Slf4j
public class ClientController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){

        //第一种方式(使用RestTemplate,url写死)
//        RestTemplate restTemplate  =  new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //第二种方式（利用LoadbalancerClient获取url，然后使用restTemplate）
       /* RestTemplate restTemplate  = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"msg";
        String response = restTemplate.getForObject(url,String.class);*/
        //第三种方式 使用@loadBalanced注解
//        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);

        //使用feign通信

        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("111", "222"));
        log.info("response={}",productInfos);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        CartDTO cartDTO = new CartDTO("111",10);
        productClient.decreaseStock(Arrays.asList(cartDTO));
        return "ok";
    }
}
