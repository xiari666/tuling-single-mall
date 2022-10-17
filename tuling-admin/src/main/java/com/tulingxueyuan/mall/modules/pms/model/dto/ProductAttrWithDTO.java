package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)

@ApiModel(value="PmsProductAttrWithDTO对象", description="商品属性参数表")
public class ProductAttrWithDTO {
    private long id;
    private String name;
    private List<PmsProductAttribute> productAttributeList;
}
