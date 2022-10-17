package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Autowired
    PmsProductAttributeService pmsProductAttributeService;
    @Autowired
    PmsProductCategoryAttributeRelationMapper pmsProductCategoryAttributeRelationMapper;
    /**
     * 商品分类-商品参数；列表
     * url:'/productAttribute/list/'+cid,
     * method:'get',
     * params:params
     */
    @RequestMapping("/list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> proAttLIst(@PathVariable long cid, @RequestParam("type") Integer type, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                    @RequestParam(value = "pageSize", defaultValue = "5")
                                                                    Integer pageSize) {
        Page page = pmsProductAttributeService.list(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));

    }

    /**
     * 商品参数添加
     * url:'/productAttribute/create',
     * method:'post',
     * data:data
     */
    @RequestMapping("/create")

    public CommonResult crate(@RequestBody PmsProductAttribute pmsProductAttribute) {


        boolean result = pmsProductAttributeService.saveUpdate(pmsProductAttribute);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();

        }

    }

    /**
     * 商品参数修改
     * url:'/productAttribute/update/'+id,
     * method:'post',
     * data:data
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)

    public CommonResult update(@RequestBody PmsProductAttribute pmsProductAttribute) {


        boolean result = pmsProductAttributeService.updateById(pmsProductAttribute);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();

        }
    }

    /**
     * 初始化
     * url:'/productAttribute/'+id,
     * method:'get'
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)

    public CommonResult get(@PathVariable long id) {


        PmsProductAttribute result = pmsProductAttributeService.getById(id);

        return CommonResult.success(result);

    }

    /**
     * url:'/productAttribute/delete',
     * method:'post',
     * data:data
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)

    public CommonResult delete(@RequestParam("ids") List<Long> ids) {


        boolean result = pmsProductAttributeService.removeplus(ids);

        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();

        }
    }
    /**
     * url:'/productAttribute/attrInfo/'+productCategoryId,
     *     method:'get'
     */
    @RequestMapping(value = "/attrInfo/{cid}", method = RequestMethod.GET)

    public CommonResult getProductAttributeByCid(@RequestParam List<Long> cid) {


        List<ProductAttributeDTO> result =pmsProductCategoryAttributeRelationMapper.select(cid);


            return CommonResult.success(result);

    }
}