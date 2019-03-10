package cn.itcast.core.service;

import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.pojo.address.Provinces;

import java.util.List;

public interface AddressService {

    public List<Address> findListByLoginUser(String userName);

    void addUserAddress(Address address);

    void dele(long id);

    Address findOne(Long id);

    void updateAddress(Address address);

    List<Provinces> findByParentId(String parentId);

    List<Cities> findByParentId2(String parentId2);

    List<Areas> findByParentId3(String parentId3);
}
