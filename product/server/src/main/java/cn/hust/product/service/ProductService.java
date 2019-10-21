package cn.hust.product.service;

import cn.hust.product.common.DecreaseStockInput;
import cn.hust.product.common.ProductInfoOutput;
import cn.hust.product.dataobject.ProductInfo;

import java.util.List;


/**
 * 商品
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-07 15:20
 **/
public interface ProductService {


    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表（给订单服务用）
     * @return
     */
   List<ProductInfoOutput> findList(List<String> productIdList);

   void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);



}
