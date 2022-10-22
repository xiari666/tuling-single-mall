package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {
    //获取商品分类列表
    //   url :  /productCategory/list/  + parentId
    //   methood=get
//    params
//    pageNum: 1,
//    pageSize: 5
    Page list(long parentId, Integer pageNum, Integer pageSize);



    boolean updateNavStatus(List<Long> ids, Integer navStatus);

    boolean updateShowStatus(List<Long> ids, Integer showStatus);


    boolean deleteProductCate(Long id);


    boolean CustomSave(PmsProductCategoryDTO pmsProductCategoryDTO);



    boolean update(PmsProductCategoryDTO pmsProductCategoryDTO);


    List<PmsProductCategoryChildrenDTO> selectWithChild();
}
