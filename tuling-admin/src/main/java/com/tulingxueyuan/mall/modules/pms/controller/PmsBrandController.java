package com.tulingxueyuan.mall.modules.pms.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService pmsBrandService;
    /**
     * 品牌管理
     * url:'/brand/list',
     *     method:'get',
     *     params:params
     */
     @ApiOperation("品牌管理")
     @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult getBandList(@RequestParam(value = "keyword",defaultValue = "") String keyWord,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
         Page page=pmsBrandService.list(keyWord,pageNum,pageSize);
         return CommonResult.success(CommonPage.restPage(page));
     }
}

