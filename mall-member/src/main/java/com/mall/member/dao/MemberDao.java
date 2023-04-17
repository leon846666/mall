package com.mall.member.dao;

import com.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author liuyang
 * @email sunlightcs@gmail.com
 * @date 2023-03-26 14:21:02
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
