package cn.hust.product.client;

import cn.hust.product.common.DecreaseStockInput;
import cn.hust.product.common.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-18 13:13
 **/

@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
     List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);


    @PostMapping("/product/decreaseStock")
     void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
