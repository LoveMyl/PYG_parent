package cn.itcast.core.controller;

import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.pojo.address.Provinces;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.service.AddressService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 收货人地址管理
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    /**
     * 根据当前用户查询全部的用户地址
     *
     * @return
     */
    @RequestMapping("/findListByLoginUser")
    public List<Address> findListByLoginUser() {
        //1. 获取当前用户的登录名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //2. 根据用户名获取这个人收货地址列表
        List<Address> addressList = addressService.findListByLoginUser(userName);
        System.out.println(addressList);
        return addressList;
    }

    /**
     * 根据当前用户 添加用户地址
     *
     * @return
     */
    @RequestMapping("/addUserAddress")
    public Result addUserAddress(@RequestBody Address address) {
        //获取当前用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //设置关联用户id
        address.setUserId(userName);
        //设置创建时间
        address.setCreateDate(new Date());
        //初始化是否默认   1 默认 0 初始值
        address.setIsDefault("0");
        try {
            addressService.addUserAddress(address);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }


    @RequestMapping("/dele")
    public Result dele(Long id) {
        //进行非空判断
        try {
            if (id == null) {
                return new Result(false, "没有权限删除");
            }
            addressService.dele(id);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询编号地址
     *
     * @param id 编号
     * @return
     */
    @RequestMapping("/findOne")
    public Address findOne(Long id) {
        return addressService.findOne(id);
    }

    /**
     * 修改默认状态
     *
     * @param id 地址编号
     * @return
     */
    @RequestMapping("/updateDefaultStatus")
    public Result updateDefaultStatus(Long id) {
        try {
            //获取当前用户
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            //查询出当前用户的所有地址
            List<Address> addressList = addressService.findListByLoginUser(userName);
            //修改所有的状态未非默认
            for (Address address : addressList) {
                address.setIsDefault("0");
                addressService.updateAddress(address);
            }
            //获取当前编号地址信息
            Address addressEntity = addressService.findOne(id);
            addressEntity.setIsDefault("1");
            //设置为默认值
            updateAddress(addressEntity);
            return new Result(true,"修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"修改失败!");
        }

    }

    /**
     * 修改
     * @param address
     * @return
     */
    @RequestMapping("/updateAddress")
    public Result updateAddress(@RequestBody Address address) {
        try {
            addressService.updateAddress(address);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");

        }
    }

    /**
     * 查询省
     * @param parentId 编号
     * @return
     */
    @RequestMapping("/findByParentId")
    public List<Provinces> findByParentId(String parentId) {

        List<Provinces> list = addressService.findByParentId(parentId);
        return  list;
    }

    /**
     * 查询市
     * @param parentId2
     * @return
     */
    @RequestMapping("/findByParentId2")
    public List<Cities> findByParentId2(String parentId2) {
        List<Cities> citiesList = addressService.findByParentId2(parentId2);
        return citiesList;
    }

    /**
     * 查询区
     * @param parentId3
     * @return
     */
    @RequestMapping("/findByParentId3")
    public List<Areas> findByParentId3(String parentId3) {
        List<Areas> areasList = addressService.findByParentId3(parentId3);
        return areasList;
    }

}
