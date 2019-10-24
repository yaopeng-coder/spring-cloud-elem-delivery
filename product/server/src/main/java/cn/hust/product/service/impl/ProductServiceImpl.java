package cn.hust.product.service.impl;


import cn.hust.product.common.DecreaseStockInput;
import cn.hust.product.common.ProductInfoOutput;
import cn.hust.product.dataobject.ProductInfo;
import cn.hust.product.enums.ProductStatusEnum;
import cn.hust.product.enums.ResultEnum;
import cn.hust.product.exception.ProductException;
import cn.hust.product.repository.ProductInfoRepository;
import cn.hust.product.service.ProductService;
import cn.hust.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-07 15:36
 **/
@Service
//@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e,output);
                    return output;
                }).collect(Collectors.toList());
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        //发送Mq消息

            amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {

        List<ProductInfo> productInfoList = new ArrayList<>();
        //查找商品是否存在
        for(DecreaseStockInput input: decreaseStockInputList){
            Optional<ProductInfo> productInfoOptional = repository.findById(input.getProductId());
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //判断库存是否足够
            ProductInfo productInfo = productInfoOptional.get();
            int result = productInfo.getProductStock() - input.getProductQuantity();
            if(result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //保存数据库
            productInfo.setProductStock(result);
            repository.save(productInfo);
            productInfoList.add(productInfo);

        }

        return productInfoList;
    }
}
