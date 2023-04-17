package com.mall.member.feign;

import com.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("mall-coupon")
public interface CouponFeignSevice {

    @RequestMapping("/coupon/coupon/membercoupon")
    public R membercoupon();
}
