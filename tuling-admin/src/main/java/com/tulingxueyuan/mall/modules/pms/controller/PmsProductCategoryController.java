package com.tulingxueyuan.mall.modules.pms.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    PmsProductCategoryService pmsProductCategoryService;
    @Autowired
    PmsProductCategoryMapper pmsProductCategoryMapper;
//商品分类列表  分页显示
    //   url :  /productCategory/list/  + parentId
    //   methood=get
//    params
//    pageNum: 1,
//    pageSize: 5
    @ApiOperation("商品分类列表")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable long parentId,
                                                                @RequestParam(value = "pageNum", defaultValue = "1")
                                                                Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "5")
                                                                Integer pageSize) {
        Page page = pmsProductCategoryService.list(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }
//导航栏状态
    //    url:'/productCategory/update/navStatus',
//    method:'post',
//    data:data
//     ids.push(row.id)  // 当前id
//            data.append('ids',ids);
//        data.append('navStatus',row.navStatus);
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    public CommonResult updateNavStatus(@RequestParam(value = "ids")
                                        List<Long> ids,
                                        @RequestParam(value = "navStatus")
                                        Integer navStatus) {
        boolean result = pmsProductCategoryService.updateNavStatus(ids, navStatus);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }
//是否显示
//    url:'/productCategory/update/showStatus',
//    method:'post',
//    data:data
//     data.append('ids',ids);
//        data.append('showStatus',row.showStatus);
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus( @RequestBody @RequestParam(value = "ids")
                                        List<Long> ids,
                                        @RequestBody
                                        @RequestParam(value = "showStatus")
                                        Integer showStatus) {
        boolean result = pmsProductCategoryService.updateShowStatus(ids, showStatus);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

//根据id删除分类
//    url:'/productCategory/delete/'+id,
//    method:'post'
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult deleteProductCate(@PathVariable(value = "id")
                                         Long id) {
         boolean result =pmsProductCategoryService.deleteProductCate(id);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }
    //商品分类添加
    //    url:'/productCategory/create',
    //    method:'post',
    //    data:data
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createProductCate(@RequestBody PmsProductCategoryDTO pmsProductCategoryDTO) {
        boolean result =pmsProductCategoryService.CustomSave(pmsProductCategoryDTO);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }
    //    商品分类编辑
    //    url:'/productCategory/update/'+id,
    //    method:'post',
    //    data:data
@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
public CommonResult updateProductCate(@RequestBody PmsProductCategoryDTO pmsProductCategoryDTO) {
    boolean result =pmsProductCategoryService.update(pmsProductCategoryDTO);
    if (result) {
        return CommonResult.success(result);
    } else {
        return CommonResult.failed();
    }
}

@RequestMapping(value = "/{id}", method = RequestMethod.GET)
public CommonResult<PmsProductCategory> getProductCateId(@PathVariable long id) {
    PmsProductCategory result =pmsProductCategoryService.getById(id);
    return CommonResult.success(result);
    }

    /**
     * 商品列表下商品分类初始化
     * url:'/productCategory/list/withChildren',
     *     method:'get'
     */

    @RequestMapping(value = "list/withChildren", method = RequestMethod.GET)
    public CommonResult getWithChildren() {

        List<PmsProductCategoryChildrenDTO> list=pmsProductCategoryService.selectWithChild();
        return CommonResult.success(list);
    }

}


