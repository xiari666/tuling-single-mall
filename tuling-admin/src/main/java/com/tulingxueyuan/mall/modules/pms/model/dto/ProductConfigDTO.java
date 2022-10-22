package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductConfigDTO商品列表筛选条件", description="商品列表筛选参数接收")
public class ProductConfigDTO {
    /**
     *       //    keyword: null,
     *       //    pageNum: 1,
     *       //    pageSize: 5,
     *       //    publishStatus: null,
     *       //    verifyStatus: null,
     *       //    productSn: null,
     *       //    productCategoryId: null,
     *       //    brandId: null
     */
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Integer publishStatus;
    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;
    private String productSn;
    private Long productCategoryId;
    private Long brandId;
}
