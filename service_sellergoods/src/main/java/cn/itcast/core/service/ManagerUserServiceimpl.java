package cn.itcast.core.service;

import cn.itcast.core.dao.user.UserDao;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.user.User;
import cn.itcast.core.pojo.user.UserQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ManagerUserServiceimpl implements ManagerUserService {
    @Autowired
    private UserDao userDao;




    @Override
    public void updateStatus(Long[] ids, String status) {
        User user = new User();

        user.setStatus(status);
        if (ids != null) {
            for (Long id : ids) {
                user.setId(id);
                userDao.updateByPrimaryKeySelective(user);
            }
        }
    }
    /**
     * 用户管理页面分页
     * @param page
     * @param rows
     * @param user
     * @return
     */

    @Override
    public PageResult findPage(Integer page, Integer rows, User user) {
        //条件对象
        UserQuery query = new UserQuery();
        if (user!=null) {
            UserQuery.Criteria criteria = query.createCriteria();
            if (user.getName() != null && !"".equals(user.getName())) {
               criteria.andUsernameEqualTo(user.getName());
            }
        }
        //分页助手
        PageHelper.startPage(page, rows);

        Page<User> orderList = (Page<User>) userDao.selectByExample(query);

        return new PageResult(orderList.getTotal(), orderList.getResult());
    }


}

