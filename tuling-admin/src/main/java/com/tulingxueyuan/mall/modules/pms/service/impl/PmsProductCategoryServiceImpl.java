package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-08
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {
    @Autowired
    PmsProductCategoryAttributeRelationService pmsProductCategoryAttributeRelationService;
    @Autowired
    PmsProductCategoryMapper pmsProductCategoryMapper;
    @Override
    public Page list(long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        //条件构造器
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("parent_id",parentId); //绑定数据库列
        queryWrapper.lambda().eq(PmsProductCategory::getParentId, parentId);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNavStatus(List<Long> ids, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        //根据ids修改navstatus显示状态
        pmsProductCategoryUpdateWrapper.lambda().set(PmsProductCategory::getNavStatus, navStatus).in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda().set(PmsProductCategory::getShowStatus, showStatus).in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    public boolean deleteProductCate(Long id) {
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);

        return this.removeById(id);
    }

    //批量保存数据加入事物

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public boolean CustomSave(PmsProductCategoryDTO pmsProductCategoryDTO) {

        //保存商品分类id
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        //通过BeanUtils将pmsProductCategoryDTO数据拷贝到pmsProductCategory
        BeanUtils.copyProperties(pmsProductCategoryDTO, pmsProductCategory);
        //由于表单没有维护分类级别，商品数量，需要设置默认值
        //设置默认数量0
        pmsProductCategory.setProductCount(0);
        extracted(pmsProductCategoryDTO, pmsProductCategory);
        return true;
    }
    //抽取接口
    private void extracted(PmsProductCategoryDTO pmsProductCategoryDTO, PmsProductCategory pmsProductCategory) {
        //通过有无上级分类设置级别

        if (pmsProductCategory.getParentId() == 0) {
            pmsProductCategory.setLevel(0);
        } else {
            //由于我们只有二级分类所以写死，否则需要查询parent_id
            pmsProductCategory.setLevel(1);
        }
        this.save(pmsProductCategory);

        //根据前端需要数据
        List<Long> productCategoryDTOList = pmsProductCategoryDTO.getProductAttributeIdList();

        List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();

        for (Long attId : productCategoryDTOList) {
            PmsProductCategoryAttributeRelation pmsProductCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
            pmsProductCategoryAttributeRelation.setProductCategoryId(pmsProductCategory.getId());
            pmsProductCategoryAttributeRelation.setProductAttributeId(attId);
            list.add(pmsProductCategoryAttributeRelation);
        }


        pmsProductCategoryAttributeRelationService.saveBatch(list);
    }

    @Override
    public boolean update(PmsProductCategoryDTO pmsProductCategoryDTO) {
        //保存商品分类id

        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        //通过BeanUtils将pmsProductCategoryDTO数据拷贝到pmsProductCategory
        BeanUtils.copyProperties(pmsProductCategoryDTO, pmsProductCategory);
        //由于表单没有维护分类级别，商品数量，需要设置默认值
        //设置默认数量0
        QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId,pmsProductCategory.getId());
        pmsProductCategoryAttributeRelationService.remove(queryWrapper);

        extracted(pmsProductCategoryDTO, pmsProductCategory);

        return true;

    }

    @Override
    public List<PmsProductCategoryChildrenDTO> selectWithChild() {

        return  pmsProductCategoryMapper.selectWithChildren();
    }
}
