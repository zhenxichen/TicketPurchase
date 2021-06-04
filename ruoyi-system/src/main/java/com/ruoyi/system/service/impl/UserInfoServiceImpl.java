package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.service.IUserInfoService;

/**
 * 用户信息补充Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-04
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService 
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息补充
     * 
     * @param userId 用户信息补充ID
     * @return 用户信息补充
     */
    @Override
    public UserInfo selectUserInfoById(Long userId)
    {
        return userInfoMapper.selectUserInfoById(userId);
    }

    /**
     * 查询用户信息补充列表
     * 
     * @param userInfo 用户信息补充
     * @return 用户信息补充
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo)
    {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增用户信息补充
     * 
     * @param userInfo 用户信息补充
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo)
    {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 修改用户信息补充
     * 
     * @param userInfo 用户信息补充
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo)
    {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除用户信息补充
     * 
     * @param userIds 需要删除的用户信息补充ID
     * @return 结果
     */
    @Override
    public int deleteUserInfoByIds(Long[] userIds)
    {
        return userInfoMapper.deleteUserInfoByIds(userIds);
    }

    /**
     * 删除用户信息补充信息
     * 
     * @param userId 用户信息补充ID
     * @return 结果
     */
    @Override
    public int deleteUserInfoById(Long userId)
    {
        return userInfoMapper.deleteUserInfoById(userId);
    }
}
