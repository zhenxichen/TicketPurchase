package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Set;

import com.ruoyi.common.core.domain.model.*;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserInfoService;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author Zhenxi Chen
 */
@RestController
public class SysLoginController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private IUserInfoService iuserInfoService;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    private static final String USER_TYPE = "userType";

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 手机号登录方法
     *
     * @param loginBody 手机号登录信息
     * @return 返回的Ajax结构体
     */
    @PostMapping("/login/phone")
    public AjaxResult loginByPhone(@RequestBody PhoneLoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        LoginRes res = loginService.loginByPhone(loginBody.getPhone(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, res.getToken());
        ajax.put(USER_TYPE, res.getType());
        return ajax;
    }

    /**
     * 微信快捷登录方法
     *
     * @param loginBody 微信登录信息
     * @return 返回的Ajax结构体
     */
    @PostMapping("/login/wechat")
    public AjaxResult loginByWechat(@RequestBody WechatLoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        LoginRes res = loginService.loginByWechat(loginBody.getOpenid());
        ajax.put(Constants.TOKEN, res.getToken());
        ajax.put(USER_TYPE, res.getType());
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
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
