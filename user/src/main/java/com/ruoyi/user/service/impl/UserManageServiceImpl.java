package com.ruoyi.user.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.user.domain.vo.UserManageVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户管理功能 服务层接口实现
 * @author Zhenxi Chen
 * @date 2021/6/7
 */
public class UserManageServiceImpl {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 获取用户列表
     *
     * @param user 前端传来的SysUser对象
     * @return
     */
    public List<UserManageVO> selectUserList(SysUser user) {
        List<SysUser> sysUserList = userMapper.selectUserList(user);
        return null;
    }

    /**
     * 将SysUser对象转为UserManageVO对象
     * @param user SysUser对象
     * @return UserManageVO对象
     */
    private UserManageVO sysUserToVO(SysUser user) {
        return null;
    }
}
