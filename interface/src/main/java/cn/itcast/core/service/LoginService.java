package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.BuyerCollect;

import java.util.List;

public interface LoginService {
    public List<BuyerCollect> findAllCollect();
}
