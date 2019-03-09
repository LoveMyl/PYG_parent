package cn.itcast.core.controller;

import cn.itcast.core.service.UserService;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: pyg_parent
 * @author: 刘家兴
 * @create: 2019-03-06 16:18
 * @version: 1.0
 **/
@RestController
@RequestMapping("login")
public class LoginController {

    @Reference
    private UserService userService;

    /**
     * 我的订单页面, 显示用户名
     * @return 用户名
     */
    @RequestMapping("name")
    public Map getUserName(){
        //获取当前登陆用户名
        String loginUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,String> loginName = new HashMap<>(16);
        if (loginUserName != null) {
            loginName.put("loginName", loginUserName);
        }else {
            loginName.put("loginName", "未登录");
        }
        return loginName;
    }
}
