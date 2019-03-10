package cn.itcast.core.controller;


import cn.itcast.core.pojo.seller.Seller;
import cn.itcast.core.service.ManagerCountService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kk")
public class ManagerController {
    @Reference
    private ManagerCountService managerCountService;

    @RequestMapping("/search")
    public int Count() {

        return managerCountService.Count("size");
    }
}
