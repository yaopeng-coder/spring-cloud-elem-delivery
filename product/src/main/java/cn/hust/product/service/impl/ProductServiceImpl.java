package cn.hust.product.service.impl;


import cn.hust.product.dataobject.ProductInfo;
import cn.hust.product.enums.ProductStatusEnum;
import cn.hust.product.repository.ProductInfoRepository;
import cn.hust.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
  //  @Cacheable(key = "123")
    public ProductInfo findOne(String productId) {

        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
//    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }





}
