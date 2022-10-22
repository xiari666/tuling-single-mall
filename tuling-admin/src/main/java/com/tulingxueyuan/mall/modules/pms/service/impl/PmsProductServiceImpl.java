package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductConfigDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Override
    public Page list(ProductConfigDTO productConfigDTO) {
        Page page=new Page(productConfigDTO.getPageNum(),productConfigDTO.getPageSize());
        QueryWrapper<PmsProduct> queryWrapper=new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambdaQueryWrapper=queryWrapper.lambda();
        //根据商品名称
        if (!StrUtil.isBlank(productConfigDTO.getKeyword())){
            lambdaQueryWrapper.like(PmsProduct::getKeywords,productConfigDTO.getKeyword());
        }
        //根据商品货号
        if (!StrUtil.isBlank(productConfigDTO.getProductSn())){
            lambdaQueryWrapper.eq(PmsProduct::getProductSn,productConfigDTO.getProductSn());
        }

        //根据商品分类
        if (productConfigDTO.getProductCategoryId()!=null &&productConfigDTO.getProductCategoryId()>0){
            lambdaQueryWrapper.eq(PmsProduct::getProductCategoryId,productConfigDTO.getProductCategoryId());
        }
        //根据商品品牌
        if (productConfigDTO.getBrandId()!=null&&productConfigDTO.getBrandId()>0){
            lambdaQueryWrapper.eq(PmsProduct::getBrandId,productConfigDTO.getBrandId());
        }
        //根据商品上架状态
        if (productConfigDTO.getPublishStatus()!=null){
            lambdaQueryWrapper.eq(PmsProduct::getPublishStatus,productConfigDTO.getPublishStatus());
        }
        //根据商品审核状态
        if (productConfigDTO.getVerifyStatus()!=null) {
            lambdaQueryWrapper.eq(PmsProduct::getVerifyStatus,productConfigDTO.getVerifyStatus());
        }
        return this.page(page,lambdaQueryWrapper);
    }
}
