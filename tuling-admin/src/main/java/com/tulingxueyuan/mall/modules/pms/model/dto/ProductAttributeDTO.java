package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

@ApiModel(value="PmsProductAttributeDTO类型和属性管理数据", description="用于商品类型属性初始化")
public class ProductAttributeDTO {
    //商品类型id
    private Long attributeCategoryId;
    //商品属性id
    private Long attributeId;
}
