package com.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.mall.product.entity.AttrEntity;
import com.mall.product.service.AttrAttrgroupRelationService;
import com.mall.product.service.AttrService;
import com.mall.product.service.CategoryService;
import com.mall.product.vo.AttrGroupRelationVo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mall.product.entity.AttrGroupEntity;
import com.mall.product.service.AttrGroupService;
import com.common.utils.PageUtils;
import com.common.utils.R;



/**
 * 属性分组
 *
 * @author liuyang
 * @email sunlightcs@gmail.com
 * @date 2023-03-26 15:10:33
 */
@RestController
@RequestMapping("product/attrgroup")
@AllArgsConstructor
public class AttrGroupController {
    private AttrGroupService attrGroupService;

    private CategoryService categoryService;

    private AttrService attrService;

    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    // @RequiresPermissions("product:attrgroup:list")
    public R attrNoRelation(@RequestParam Map<String, Object> params,@PathVariable("attrgroupId") Long attrgroupId){
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    // @RequiresPermissions("product:attrgroup:list")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> list =  attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data", list);
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{categoryId}")
   // @RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("categoryId") Long categoryId){
        PageUtils page = attrGroupService.queryPage(params,categoryId);

        return R.ok().put("page", page);
    }

    /**
     * add
     */
    @RequestMapping("/attr/relation")
    //@RequiresPermissions("product:attr:delete")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos){
        attrAttrgroupRelationService.saveBatch(vos);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/attr/relation/delete")
    //@RequiresPermissions("product:attr:delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();

        attrGroup.setCatelogPath(categoryService.getCategoryPath(catelogId));
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
