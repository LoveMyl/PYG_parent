package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.user.User;
import cn.itcast.core.service.ManagerUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Reference
    private ManagerUserService managerUserService;


    @RequestMapping("search")
    public PageResult search(Integer page, Integer rows, @RequestBody User user) {
        return managerUserService.findPage(page, rows, user);
    }


    @RequestMapping("updateStatus")
    public Result updateStatus(Long[] ids, String status) {
        try {
            managerUserService.updateStatus(ids, status);
            return new Result(true, "修改数据状态成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改数据状态失败!");
        }
    }




}
