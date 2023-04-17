package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageUtils;
import com.mall.order.entity.SmsHomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author liuyang
 * @email sunlightcs@gmail.com
 * @date 2023-03-26 14:35:46
 */
public interface SmsHomeSubjectService extends IService<SmsHomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

