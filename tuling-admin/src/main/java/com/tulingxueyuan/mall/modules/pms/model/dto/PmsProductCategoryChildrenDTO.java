package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品分类一级二级级联列表PmsProductCategoryChildrenDTO对象", description="商品列表中商品分类一二级联")
public class PmsProductCategoryChildrenDTO {
        private Long id;
        private String name;
        private List<PmsProductCategory> children;
    }


