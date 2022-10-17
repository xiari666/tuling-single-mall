package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    Page list(Integer pageNum, Integer pageSize);

    boolean addAttCat(PmsProductAttributeCategory pmsProductAttributeCategory);
}
