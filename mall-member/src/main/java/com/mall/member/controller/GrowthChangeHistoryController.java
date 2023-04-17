package com.mall.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.mall.member.entity.MemberEntity;
import com.mall.member.feign.CouponFeignSevice;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.member.entity.GrowthChangeHistoryEntity;
import com.mall.member.service.GrowthChangeHistoryService;
import com.common.utils.PageUtils;
import com.common.utils.R;



/**
 * 成长值变化历史记录
 *
 * @author liuyang
 * @email sunlightcs@gmail.com
 * @date 2023-03-26 14:21:02
 */
@RestController
@RequestMapping("member/growthchangehistory")
@AllArgsConstructor
public class GrowthChangeHistoryController {

    private GrowthChangeHistoryService growthChangeHistoryService;
    private CouponFeignSevice couponFeignSevice;

    /**
     * 列表
     */
    @RequestMapping("/getMemberCoupons")
    // @RequiresPermissions("member:growthchangehistory:list")
    public R getMemberCoupons(@RequestParam Map<String, Object> params){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("zhangsan");
        R membercoupon = couponFeignSevice.membercoupon();
        return R.ok().put("member",memberEntity).put("coupons",membercoupon.get("coupons"));
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("member:growthchangehistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = growthChangeHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:growthchangehistory:info")
    public R info(@PathVariable("id") Long id){
		GrowthChangeHistoryEntity growthChangeHistory = growthChangeHistoryService.getById(id);

        return R.ok().put("growthChangeHistory", growthChangeHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:growthchangehistory:save")
    public R save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory){
		growthChangeHistoryService.save(growthChangeHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:growthchangehistory:update")
    public R update(@RequestBody GrowthChangeHistoryEntity growthChangeHistory){
		growthChangeHistoryService.updateById(growthChangeHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:growthchangehistory:delete")
    public R delete(@RequestBody Long[] ids){
		growthChangeHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
