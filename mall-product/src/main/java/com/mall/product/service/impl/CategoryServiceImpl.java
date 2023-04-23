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

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public Long[] getCategoryPath(Long catelogId) {

        List<Long> paths = new ArrayList<>();

        /// recursively get find parent id
        List<Long> parentPath = findParentPath(catelogId, paths);

        // sort reversely
        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {

        //1、get the current cate id
        paths.add(catelogId);

        //2. find the category detail of the current
        CategoryEntity byId = this.getById(catelogId);
        // 3. if the parent is not the top level, then continue to find the parent category
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }

        return paths;
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