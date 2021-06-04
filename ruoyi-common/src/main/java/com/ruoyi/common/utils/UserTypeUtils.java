package com.ruoyi.common.utils;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用于进行用户类型格式转换的工具
 */
public class UserTypeUtils {
    /**
     * 将用户的类型转为返回给前端的格式
     * @param roles 存储roleID的list
     * @return "0"表示普通用户，"1"表示员工，"2"表示司机
     */
    public static String getUserTypeString(List<Integer> roles) {
        Set<Integer> roleIdSet = new HashSet<>();
        for (int role: roles) {
            roleIdSet.add(role);
        }
        if (roleIdSet.contains(UserConstants.DRIVER_DB)) {
            return UserConstants.DRIVER;
        }
        if (roleIdSet.contains(UserConstants.EMPLOYEE_DB)) {
            return UserConstants.EMPLOYEE;
        }
        return UserConstants.NORMAL_USER;
    }
}
