package cn.hust.product.service.impl;


import cn.hust.product.dataobject.ProductCategory;
import cn.hust.product.repository.ProductCategoryRepository;
import cn.hust.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-07 13:51
 **/

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;


    @Override
    public ProductCategory findOne(Integer categoryId) {
        /**
         * 查不到返回null
         * .get 查不到抛异常
         */
        return repository.findById(categoryId).orElse(null);
    }


    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
