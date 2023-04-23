package com.mall.product.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;

import com.mall.product.dao.AttrGroupDao;
import com.mall.product.entity.AttrGroupEntity;
import com.mall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        IPage<AttrGroupEntity> page = null;
        if (categoryId == 0) {
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    new QueryWrapper<AttrGroupEntity>()
            );
        } else {
            String key = params.get("key").toString();
            QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", categoryId);
            if(StringUtils.isNotEmpty(key)){
                wrapper.and(obj->{
                    obj.eq("attr_group_id",key).or().like("attr_group_name",key);
                });
            }
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
        }

        return new PageUtils(page);
    }

}