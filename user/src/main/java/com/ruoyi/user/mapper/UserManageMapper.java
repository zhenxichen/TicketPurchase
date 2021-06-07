package com.ruoyi.user.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.user.domain.dto.UserManageDTO;

import java.util.List;

/**
 * 用户管理功能数据层接口
 *
 * @author Zhenxi Chen
 */
public interface UserManageMapper {
    /**
     * 查询用于用户管理界面展示的数据
     * @return
     */
    List<UserManageDTO> selectUserList(SysUser user);


}
