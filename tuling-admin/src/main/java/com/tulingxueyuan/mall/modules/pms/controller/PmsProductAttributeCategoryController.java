package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttrWithDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeCategoryController {
    @Autowired
    PmsProductAttributeCategoryService pmsProductAttributeCategoryService;
    @Autowired
    PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;
//    商品类型
//    url:'/productAttribute/category/list',
//    method:'get',
//    params:params
    @RequestMapping("/category/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> pmsAttCat(@RequestParam(value = "pageNum", defaultValue = "1")
                                                                         Integer pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "5")
                                                                         Integer pageSize){

       Page reslut = pmsProductAttributeCategoryService.list(pageNum,pageSize);
       return CommonResult.success(CommonPage.restPage(reslut));
    }
/**
 * 添加商品类型
 *  return request({
 *         url:'/productAttribute/category/create',
 *                 method:'post',
 *                 data:data
 */

    @RequestMapping(value = "/category/create",method = RequestMethod.POST)
    public CommonResult  categoryCommonResult(PmsProductAttributeCategory pmsProductAttributeCategory) {

        boolean reslt = pmsProductAttributeCategoryService.addAttCat(pmsProductAttributeCategory);
        if (reslt) {
            return CommonResult.success(reslt);
        } else {
            return CommonResult.failed();
        }
    }
/**
 * 修改商品类型
 *  url:'/productAttribute/category/update/'+id,
 *     method:'post',
 *     data:data
 */
        @RequestMapping(value = "category/update/{id}",method = RequestMethod.POST)
        public CommonResult  UpdateProAtt(PmsProductAttributeCategory pmsProductAttributeCategory){

            boolean reslt=pmsProductAttributeCategoryService.updateById(pmsProductAttributeCategory);
            if (reslt){
                return CommonResult.success(reslt);
            }else {
                return CommonResult.failed();
            }
    }
    /**
     * 删除商品类型
     *  url:'/productAttribute/category/delete/'+id,
     *     method:'get'
     */
    @RequestMapping(value = "category/delete/{id}",method = RequestMethod.GET)
    public CommonResult  deleteProAtt(PmsProductAttributeCategory pmsProductAttributeCategory){

        boolean reslt=pmsProductAttributeCategoryService.removeById(pmsProductAttributeCategory);
        if (reslt){
            return CommonResult.success(reslt);
        }else {
            return CommonResult.failed();
        }
}
    /**
     * 初始化商品分类筛选属性
     *  url:'/productAttribute/category/list/withAttr',
     *     method:'get'
     */
    @RequestMapping(value = "/category/list/withAttr", method = RequestMethod.GET)

    public CommonResult getListWithAttr() {
        List<ProductAttrWithDTO> list=pmsProductAttributeCategoryMapper.getListWithAttr();
        return CommonResult.success(list);


    }
}
