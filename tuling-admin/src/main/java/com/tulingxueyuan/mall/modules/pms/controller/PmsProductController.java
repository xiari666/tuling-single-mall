package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
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

      @ApiOperation("商品列表")
      @RequestMapping(value = "/list",method = RequestMethod.GET)
      public CommonResult<List<PmsProduct>> List(){
           List<PmsProduct> list=pmsProductService.list();
           return CommonResult.success(list);

      }
}

