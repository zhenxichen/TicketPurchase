package com.ruoyi.user.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.user.domain.dto.UserManageDTO;

import java.util.List;

/**
 * 用户管理功能 服务层接口
 *
 * @author Zhenxi Chen
 * @date 2021/6/7
 */
public interface IUserManageService {

    /**
     * 获取用户列表
     *
     * @param user 前端传来的SysUser对象
     * @return
     */
    List<UserManageDTO> selectUserList(SysUser user);
}
