package com.ruoyi.user.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.user.domain.dto.UserManageDTO;
import com.ruoyi.user.domain.vo.UserExcelVO;

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

    /**
     * 获取用于导出到Excel的列表
     *
     * @param user 前端传来的SysUser对象，用于进行筛选
     * @return
     */
    List<UserExcelVO> selectUserExportList(SysUser user);

    /**
     * 从Excel当中导入用户信息
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否允许更新原有用户数据
     * @param operName 操作人员用户名
     * @return 导入结果信息
     */
    String importUser(List<UserExcelVO> userList, Boolean isUpdateSupport, String operName);

    /**
     * 添加新用户
     *
     * @param user SysUser对象
     * @param vo VO对象
     * @return
     */
    int insertUser(SysUser user, UserExcelVO vo);

    /**
     * 更新用户信息
     *
     * @param user SysUser对象
     * @param vo UserExcelVO对象
     * @return
     */
    int updateUser(SysUser user, UserExcelVO vo);
}
