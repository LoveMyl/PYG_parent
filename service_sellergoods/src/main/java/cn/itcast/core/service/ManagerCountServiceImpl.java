package cn.itcast.core.service;

import cn.itcast.core.dao.seller.SellerDao;
import cn.itcast.core.pojo.seller.Seller;
import cn.itcast.core.pojo.seller.SellerQuery;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ManagerCountServiceImpl implements ManagerCountService {
    @Autowired
    private SellerDao sellerDao;

    @Override
    public int Count(String seller) {
        List<Seller> sellers = sellerDao.selectByExample(null);
        int size = sellers.size();
        return size;
    }
}
