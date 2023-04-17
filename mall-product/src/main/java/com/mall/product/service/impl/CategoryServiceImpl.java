package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.mall.product.dao.CategoryDao;
import com.mall.product.entity.CategoryEntity;
import com.mall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(new Query<CategoryEntity>().getPage(params), new QueryWrapper<CategoryEntity>());

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree(Map<String, Object> params) {
        //1 get all category
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        List<CategoryEntity> collect = categoryEntities.stream().filter(entry -> entry.getParentCid() == 0).map(menu -> {
            //2. find children menu
            menu.setChildren(getCatChildern(menu, categoryEntities));
            return menu;
        }).sorted(
                //3. sort
                (menu1,menu2)->{
            return (menu1.getSort()==null? 0: menu1.getSort()) - (menu2.getSort()==null?0 : menu2.getSort());
        }) .collect(Collectors.toList());


        return collect;
    }

    @Override
    public void deleteBatchIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);
    }

    // recursively get all children menu
    private List<CategoryEntity> getCatChildern(CategoryEntity entities, List<CategoryEntity> all) {
        List<CategoryEntity> collect = all.stream().filter(child -> child.getParentCid() == entities.getCatId())
                .map(entity -> {
                    entity.setChildren(getCatChildern(entity, all));
                    return entity;
                }).collect(Collectors.toList());

        return collect;

    }

}