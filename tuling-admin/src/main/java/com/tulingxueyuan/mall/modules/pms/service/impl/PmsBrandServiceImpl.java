package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsBrandMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {


    @Override
    public Page list(String keyWord, Integer pageNum, Integer pageSize) {
        Page page=new Page(pageNum,pageSize);
        QueryWrapper<PmsBrand> queryWrapper=new QueryWrapper<>();
        if (!BeanUtil.isEmpty(keyWord)){
            queryWrapper.lambda().like(PmsBrand::getName,keyWord);
        }else {
            queryWrapper.lambda().orderByDesc(PmsBrand::getSort);
        }

        return this.page(page,queryWrapper);
    }
}
