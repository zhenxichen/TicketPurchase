package com.ruoyi.user.util;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.user.domain.dto.UserManageDTO;
import com.ruoyi.user.domain.vo.UserExcelVO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 对存储用户信息的对象进行转换的工具类
 *
 * @author Zhenxi Chen
 * @date 2021/6/8
 */
public class UserFormatUtil {
    private static final long ADMIN = 1L;       // 超级管理员

    private static final long DRIVER = 101L;    // 司机

    private static final long EMPLOYEE = 100L;  // 员工

    private static final long COMMON = 2L;      // 普通用户

    /**
     * 将UserManageDTO转为UserExcelVO
     * @param dto UserManageDTO对象
     * @return VO对象
     */
    public static UserExcelVO transferDTO2ExcelVO(UserManageDTO dto) {
        UserExcelVO vo = new UserExcelVO();
        vo.setUserId(dto.getUserId());
        vo.setUsername(dto.getUsername());
        vo.setNickName(dto.getNickName());
        vo.setPhonenumber(dto.getPhonenumber());
        vo.setStatus(dto.getStatus());
        vo.setRole(getRoleNameFromDTO(dto));
        return vo;
    }

    /**
     * 从UserManageDTO对象中获取角色ID
     * @param dto
     * @return
     */
    public static String getRoleNameFromDTO(UserManageDTO dto) {
        List<SysRole> roles = dto.getRoles();
        Set<Long> roleSet = new HashSet<>();
        for (SysRole role: roles) {
            roleSet.add(role.getRoleId());
        }
        Long ret;
        if (roleSet.contains(ADMIN)) {
            ret = ADMIN;
        } else if (roleSet.contains(DRIVER)) {
            ret = DRIVER;
        } else if (roleSet.contains(EMPLOYEE)) {
            ret = EMPLOYEE;
        } else if (roleSet.contains(COMMON)) {
            ret = COMMON;
        } else {
            return "";
        }
        return String.valueOf(ret);
    }
}
