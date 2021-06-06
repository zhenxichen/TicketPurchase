package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserOpenIdDTO;

/**
 * 用户信息补充Service接口
 * 
 * @author Zhenxi Chen
 * @date 2021-06-06
 */
public interface IUserInfoService 
{
    /**
     * 查询用户信息补充
     * 
     * @param userId 用户信息补充ID
     * @return 用户信息补充
     */
    UserInfo selectUserInfoById(Long userId);

    /**
     * 查询用户信息补充列表
     * 
     * @param userInfo 用户信息补充
     * @return 用户信息补充集合
     */
    List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增用户信息补充
     * 
     * @param userInfo 用户信息补充
     * @return 结果
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息补充
     * 
     * @param userInfo 用户信息补充
     * @return 结果
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除用户信息补充
     * 
     * @param userIds 需要删除的用户信息补充ID
     * @return 结果
     */
    int deleteUserInfoByIds(Long[] userIds);

    /**
     * 删除用户信息补充信息
     * 
     * @param userId 用户信息补充ID
     * @return 结果
     */
    int deleteUserInfoById(Long userId);

    /**
     * 通过open ID获取用户名
     *
     * @param openId open ID
     * @return
     */
    UserOpenIdDTO selectUsernameByOpenId(String openId);
}
