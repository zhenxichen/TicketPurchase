package com.ruoyi.userInfoDetail;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;

import com.ruoyi.common.exception.user.PhoneNumberNotUniqueException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserInfoService;
import com.ruoyi.user.domain.dto.UserModifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/ticketMiniProgram")
public class UserInfoDetailController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private IUserInfoService iuserInfoService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @RequestMapping("/index")
    public Object add() throws Exception {
        return "index";
    }
    /**
     * 获取用户详细信息
     */
    @GetMapping("/userInfo")
    public AjaxResult userInfo()
    {
        String oldUserName = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(oldUserName);  //初始表用户信息
        Long userID = user.getUserId();  //userID
        String username = user.getUserName(); //username
        String nickName = user.getNickName(); //nickName
        String phoneNumber = user.getPhonenumber();  //phoneNumber
        Set<String> userType = roleService.selectRolePermissionByUserId(userID);
//        String type = userType.iterator().next();   //userType
        HashSet userTypeChinese=new HashSet<>();
        for (String str : userType) {
            if(str.equals("driver"))
                userTypeChinese.add("司机");
            if(str.equals("employee"))
                userTypeChinese.add("员工");
            if(str.equals("common"))
                userTypeChinese.add("普通角色");
            if(str.equals("admin"))
                userTypeChinese.add("超级管理员");
        }
        UserInfo userInfo = iuserInfoService.selectUserInfoById(userID);  //新增表中的用户信息
        String IDCard = userInfo.getIdCard();  //IDCard

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userID", userID);
        dataMap.put("username", username);
        dataMap.put("nickName", nickName);
        dataMap.put("phoneNumber", phoneNumber);
        dataMap.put("userType", userTypeChinese);
        dataMap.put("IDCard", IDCard);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", dataMap);

        return ajax;
    }

    /**
     * 修改用户详细信息
     */
    @RepeatSubmit
    @PostMapping("/userInfo")
    public AjaxResult userInfoModify(@RequestBody UserModifyDTO userModifyDTO)
    {
        String oldUserName = SecurityUtils.getUsername();
        SysUser oldUser = userService.selectUserByUserName(oldUserName);
        Long userId = oldUser.getUserId();
        String phone = userModifyDTO.getPhoneNumber();
        if (StringUtils.isNotEmpty(phone)) {
            oldUser.setPhonenumber(phone);
            if (userService.checkPhoneUnique(oldUser) == UserConstants.NOT_UNIQUE) {
                throw new PhoneNumberNotUniqueException();
            }
        }
        String nickname = userModifyDTO.getNickName();
        if (StringUtils.isNotEmpty(nickname)) {
            oldUser.setNickName(nickname);
        }
        String password = userModifyDTO.getPwd();
        if (StringUtils.isNotNull(password) && StringUtils.isNotEmpty(password)) {
            String newpwd = SecurityUtils.encryptPassword(userModifyDTO.getPwd());
            oldUser.setPassword(newpwd);
        }
        userService.updateUser(oldUser);
        UserInfo oldUserInfo = iuserInfoService.selectUserInfoById(userId);
        String idCard = userModifyDTO.getIdCard();
        if (StringUtils.isNotEmpty(idCard)) {
            oldUserInfo.setIdCard(userModifyDTO.getIdCard());
            iuserInfoService.updateUserInfo(oldUserInfo);
        }
        return AjaxResult.success(userModifyDTO);
    }


    /**
     * 获取用户余额
     */
    @GetMapping("/userInfo/wallet")
    public AjaxResult getUserBalance()
    {
        String oldUserName = SecurityUtils.getUsername();
        SysUser oldUser = userService.selectUserByUserName(oldUserName);
        Long userId = oldUser.getUserId();
        UserInfo userInfo = iuserInfoService.selectUserInfoById(userId);
        Long balance = userInfo.getBalance();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("balance", balance);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", dataMap);
        return ajax;
    }

    /**
     * 充值用户余额
     */
    @RepeatSubmit
    @PostMapping("/userInfo/wallet")
    public AjaxResult addUserBalance(@RequestParam("balance")Long addBalance)
    {
        String oldUserName = SecurityUtils.getUsername();
        SysUser oldUser = userService.selectUserByUserName(oldUserName);
        Long userId = oldUser.getUserId();
        UserInfo userInfo = iuserInfoService.selectUserInfoById(userId);
        userInfo.setBalance(addBalance+userInfo.getBalance());
        iuserInfoService.updateUserInfo(userInfo);
        Long balance = userInfo.getBalance();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("balance", balance);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", dataMap);
        return ajax;
    }

    @GetMapping("getOpenid")
    public AjaxResult getOpenid(@RequestParam("openId")String openId){
        String userName = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(userName);  //初始表用户信息
        Long userID = user.getUserId();  //userID
        UserInfo userInfo = iuserInfoService.selectUserInfoById(userID);  //新增表中的用户信息
        userInfo.setOpenId(openId);
        return AjaxResult.success(iuserInfoService.updateUserInfo(userInfo));
    }


}

