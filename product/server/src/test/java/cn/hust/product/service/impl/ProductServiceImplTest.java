package cn.hust.product.service.impl;

import cn.hust.product.common.DecreaseStockInput;
import cn.hust.product.common.ProductInfoOutput;
import cn.hust.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired

    @Test
    public void findUpAll() throws Exception {
    }

    @Test
    public void findList() throws Exception {

        List<ProductInfoOutput> result = productService.findList(Arrays.asList("111", "222"));
        Assert.assertNotEquals(0,result.size());
    }


    @Test
    public void descreaseStock(){
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("111",1);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }
}