package cn.itcast.core.service;

import cn.itcast.core.dao.address.AddressDao;
import cn.itcast.core.dao.address.AreasDao;
import cn.itcast.core.dao.address.CitiesDao;
import cn.itcast.core.dao.address.ProvincesDao;
import cn.itcast.core.pojo.address.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ProvincesDao provincesDao;

    @Autowired
    private CitiesDao citiesDao;

    @Autowired
    private AreasDao areasDao;

    @Override
    public List<Address> findListByLoginUser(String userName) {
        AddressQuery query = new AddressQuery();
        AddressQuery.Criteria criteria = query.createCriteria();
        criteria.andUserIdEqualTo(userName);
        List<Address> addresses = addressDao.selectByExample(query);
        return addresses;
    }

    /**
     * 添加用户 地址
     *
     * @param address
     */
    @Override
    public void addUserAddress(Address address) {
        addressDao.insertSelective(address);
    }


    /**
     * 根据用户地址编号删除
     *
     * @param
     */
    @Override
    public void dele(long id) {
        addressDao.deleteByPrimaryKey(id);
    }

    /**
     * 根据编号查询地址
     *
     * @param id 编号
     * @return
     */
    @Override
    public Address findOne(Long id) {
        return addressDao.selectByPrimaryKey(id);

    }

    /**
     * 修改地址
     *
     * @param address
     */
    @Override
    public void updateAddress(Address address) {
        addressDao.updateByPrimaryKeySelective(address);
    }

    /**
     * 查询省
     * @param parentId
     * @return
     */
    @Override
    public List<Provinces> findByParentId(String parentId) {

        List<Provinces> provincesList = provincesDao.selectByExample(null);

        return provincesList;
    }

    /**
     * 查询市
     * @param parentId2
     * @return
     */
    @Override
    public List<Cities> findByParentId2(String parentId2) {
        CitiesQuery query = new CitiesQuery();
        CitiesQuery.Criteria criteria = query.createCriteria();
        criteria.andProvinceidEqualTo(parentId2);
        List<Cities> citiesList = citiesDao.selectByExample(query);
        return citiesList;
    }

    /**
     * 查询市
     * @param parentId3
     * @return
     */
    @Override
    public List<Areas> findByParentId3(String parentId3) {
        AreasQuery query = new AreasQuery();
        AreasQuery.Criteria criteria = query.createCriteria();
        criteria.andCityidEqualTo(parentId3);
        List<Areas> areasList = areasDao.selectByExample(query);
        return areasList;
    }


}
