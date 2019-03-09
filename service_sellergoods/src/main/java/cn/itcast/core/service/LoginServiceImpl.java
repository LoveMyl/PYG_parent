package cn.itcast.core.service;

import cn.itcast.core.common.Constants;
import cn.itcast.core.pojo.entity.BuyerCollect;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<BuyerCollect> findAllCollect() {
        //获取当前登录用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //根据用户名，在redis中获取收藏列表
        List<BuyerCollect> collectList= (List<BuyerCollect>)redisTemplate.boundHashOps(Constants.REDIS_COLLECT_LIST).get(userName);
        if (collectList == null) {
            collectList = new ArrayList<BuyerCollect>();
        }
        return collectList;
    }
}
