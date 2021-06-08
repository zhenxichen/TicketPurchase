package com.ruoyi.user.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.user.domain.dto.UserManageDTO;
import com.ruoyi.user.domain.vo.UserExcelVO;
import com.ruoyi.user.mapper.UserManageMapper;
import com.ruoyi.user.service.IUserManageService;
import com.ruoyi.user.util.UserFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理功能 服务层接口实现
 * @author Zhenxi Chen
 * @date 2021/6/7
 */
@Service
public class UserManageServiceImpl implements IUserManageService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserManageMapper userManageMapper;

    /**
     * 获取用户列表
     *
     * @param user 前端传来的SysUser对象
     * @return
     */
    @Override
    public List<UserManageDTO> selectUserList(SysUser user) {
        return userManageMapper.selectUserList(user);
    }

    @Override
    public List<UserExcelVO> selectUserExportList(SysUser user) {
        List<UserManageDTO> dtoList = userManageMapper.selectUserList(user);
        List<UserExcelVO> voList = new ArrayList<>();
        for (UserManageDTO dto: dtoList) {
            voList.add(UserFormatUtil.transferDTO2ExcelVO(dto));
        }
        return voList;
    }

}
