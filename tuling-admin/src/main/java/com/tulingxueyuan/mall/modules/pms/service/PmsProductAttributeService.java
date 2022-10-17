package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    Page list(long cid, Integer type, Integer pageSize, Integer pageNum);

    boolean saveUpdate(PmsProductAttribute pmsProductAttribute);

    boolean removeplus(List<Long> ids);
}
