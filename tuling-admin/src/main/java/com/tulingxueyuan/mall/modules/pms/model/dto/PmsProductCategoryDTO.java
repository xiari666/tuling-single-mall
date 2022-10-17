package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)

@ApiModel(value="商品分类数据传输对象PmsProductCategoryDTO对象", description="商品分类添加修改")
public class PmsProductCategoryDTO extends PmsProductCategory {

    private List<Long> productAttributeIdList;
}
