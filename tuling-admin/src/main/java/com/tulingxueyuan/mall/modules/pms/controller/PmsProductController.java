package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductConfigDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {
      @Autowired
      PmsProductService pmsProductService;

      //商品列表
      //    url:'/product/list',
      //    method:'get',
      //    params:params
      //    keyword: null,
      //    pageNum: 1,
      //    pageSize: 5,
      //    publishStatus: null,
      //    verifyStatus: null,
      //    productSn: null,
      //    productCategoryId: null,
      //    brandId: null
      @ApiOperation("商品列表")
      @RequestMapping(value = "/list",method = RequestMethod.GET)
      public CommonResult List(ProductConfigDTO productConfigDTO){
           Page page=pmsProductService.list(productConfigDTO);
           return CommonResult.success(CommonPage.restPage(page));

      }

}

