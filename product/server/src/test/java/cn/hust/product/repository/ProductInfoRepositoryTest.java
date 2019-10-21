package cn.hust.product.repository;

import cn.hust.product.dataobject.ProductInfo;
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
public class ProductInfoRepositoryTest {

    @Autowired
    private  ProductInfoRepository repository;
    @Test
    public void findByProductStatus() throws Exception {
    }

    @Test
    public void findByProductIdIn() throws Exception {
        List<ProductInfo> result = repository.findByProductIdIn(Arrays.asList("111", "222"));
        Assert.assertNotEquals(0,result.size());
    }

}