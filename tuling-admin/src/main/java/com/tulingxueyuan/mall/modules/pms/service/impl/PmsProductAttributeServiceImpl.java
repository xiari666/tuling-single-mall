package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {
    @Autowired
    PmsProductAttributeCategoryService pmsProductAttributeCategoryService;
    @Autowired
    PmsProductAttributeService pmsProductAttributeService;
    @Autowired
    PmsProductAttributeMapper pmsProductAttributeMapper;

    @Override
    public Page list(long cid, Integer type, Integer pageSize, Integer pageNum) {

        Page page = new Page(pageNum, pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductAttribute::getProductAttributeCategoryId, cid);
        queryWrapper.lambda().eq(PmsProductAttribute::getType, type);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean saveUpdate(PmsProductAttribute pmsProductAttribute) {
        boolean save = this.save(pmsProductAttribute);
        UpdateWrapper<PmsProductAttributeCategory> queryWrapper = new UpdateWrapper<>();
        if (save) {
            if (pmsProductAttribute.getType() == 0) {
                //更新属性
                queryWrapper.setSql("attribute_count=attribute_count+1");
            } else if (pmsProductAttribute.getType() == 1) {
                //更新参数
                queryWrapper.setSql("param_count=param_count+1");
            }
            queryWrapper.lambda().eq(PmsProductAttributeCategory::getId, pmsProductAttribute.getProductAttributeCategoryId());
            pmsProductAttributeCategoryService.update(queryWrapper);


        }
        return save;
    }

    @Override
    public boolean removeplus(List<Long> ids) {
        //ids不为空才可执行删除
        if(ids==null){
            return false;
        }
        // 
        PmsProductAttribute pmsProductAttribute=null;
        for (Long id : ids) {
            pmsProductAttribute = this.getById(id);
            if (pmsProductAttribute != null) {
                break;
            }
        }
        //删除属性后得到删除数量 length需要放到前面 删除后拿到的数据为0 导致删除成功而更新下方失败
        int length = pmsProductAttributeMapper.deleteBatchIds(ids);


        if (length > 0 && pmsProductAttribute != null) {

            UpdateWrapper<PmsProductAttributeCategory> queryWrapper = new UpdateWrapper<>();

                if (pmsProductAttribute.getType() == 0) {
                    //更新属性
                    queryWrapper.setSql("attribute_count=attribute_count-" + length);
                } else if (pmsProductAttribute.getType() == 1) {
                    //更新参数
                    queryWrapper.setSql("param_count=param_count-" + length);
                }
                queryWrapper.lambda().eq(PmsProductAttributeCategory::getId, pmsProductAttribute.getProductAttributeCategoryId());
                pmsProductAttributeCategoryService.update(queryWrapper);




        }
            //删除的数据大于0条
            return length>0;
    }
}