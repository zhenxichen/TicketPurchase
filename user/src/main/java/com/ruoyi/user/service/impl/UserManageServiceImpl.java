package com.ruoyi.user.service.impl;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.IUserInfoService;
import com.ruoyi.user.domain.dto.UserManageDTO;
import com.ruoyi.user.domain.vo.UserExcelVO;
import com.ruoyi.user.mapper.UserManageMapper;
import com.ruoyi.user.service.IUserManageService;
import com.ruoyi.user.util.UserFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理功能 服务层接口实现
 * @author Zhenxi Chen
 * @date 2021/6/7
 */
@Service
public class UserManageServiceImpl implements IUserManageService {

    private static final Logger log = LoggerFactory.getLogger(UserManageServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserManageMapper userManageMapper;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 获取用户列表
     *
     * @param user 前端传来的SysUser对象，用于进行筛选
     * @return
     */
    @Override
    public List<UserManageDTO> selectUserList(SysUser user) {
        return userManageMapper.selectUserList(user);
    }

    /**
     * 获取用于导出到Excel的列表
     *
     * @param user 前端传来的SysUser对象，用于进行筛选
     * @return
     */
    @Override
    public List<UserExcelVO> selectUserExportList(SysUser user) {
        List<UserManageDTO> dtoList = userManageMapper.selectUserList(user);
        List<UserExcelVO> voList = new ArrayList<>();
        for (UserManageDTO dto: dtoList) {
            voList.add(UserFormatUtil.transferDTO2ExcelVO(dto));
        }
        return voList;
    }

    /**
     * 从Excel当中导入用户信息
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否允许更新原有用户数据
     * @param operName 操作人员用户名
     * @return 导入结果信息
     */
    @Override
    public String importUser(List<UserExcelVO> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;     // 统计导入成功条数
        int failureNum = 0;     // 统计导入失败条数
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (UserExcelVO vo: userList) {
            try {
                SysUser user = UserFormatUtil.createSysUserByExcelVO(vo);
                SysUser u = userMapper.selectUserByUserName(user.getUserName());     // 校验用户是否存在
                if (StringUtils.isNull(u)) {    // 若不存在
                    user.setPassword(SecurityUtils.encryptPassword(password));      // 密码需要加密后存储入数据库
                    user.setCreateBy(operName);
                    this.insertUser(user, vo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {       // 若已存在，且设置支持更新
                    user.setUpdateBy(operName);
                    this.updateUser(user, vo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + vo.getUsername() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 添加新用户
     *
     * @param user SysUser对象
     * @param vo VO对象
     * @return
     */
    @Override
    @Transactional
    public int insertUser(SysUser user, UserExcelVO vo) {
        int rows = userMapper.insertUser(user);     // 添加新用户到sys_user表中
        // 添加新用户到user_info表中
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(vo.getUserId());
        userInfo.setBalance(0L);
        userInfoService.insertUserInfo(userInfo);
        // 添加用户到sys_user_role表中
        List<SysUserRole> list = UserFormatUtil.getSysRoleListFromVO(vo);   // 获取用户角色列表
        if (list.size() > 0) {
            sysUserRoleMapper.batchUserRole(list);
        }
        return rows;
    }

    /**
     * 更新用户信息
     *
     * @param user SysUser对象
     * @param vo UserExcelVO对象
     * @return
     */
    @Override
    @Transactional
    public int updateUser(SysUser user, UserExcelVO vo) {
        // 删除原有的用户-角色关联表信息
        sysUserRoleMapper.deleteUserRoleByUserId(user.getUserId());
        // 添加用户到sys_user_role表中
        List<SysUserRole> list = UserFormatUtil.getSysRoleListFromVO(vo);   // 获取用户角色列表
        if (list.size() > 0) {
            sysUserRoleMapper.batchUserRole(list);
        }
        return userMapper.updateUser(user);
    }

    /**
     * 为用户进行充值
     *
     * @param ids 充值的用户ID数组
     * @param amount 充值金额
     * @return 成功修改的条数
     */
    @Override
    public int recharge(Long[] ids, int amount) {
        int count = 0;
        for (Long id: ids) {
            UserInfo userInfo = userInfoService.selectUserInfoById(id);
            userInfo.setBalance(userInfo.getBalance() + amount);
            count += userInfoService.updateUserInfo(userInfo);
        }
        return count;
    }

}
