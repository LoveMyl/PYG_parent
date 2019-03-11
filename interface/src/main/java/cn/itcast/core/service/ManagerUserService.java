package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.user.User;

public interface ManagerUserService {

    public PageResult findPage(Integer page, Integer rows, User user);


    void updateStatus(Long[] ids, String status);
}
