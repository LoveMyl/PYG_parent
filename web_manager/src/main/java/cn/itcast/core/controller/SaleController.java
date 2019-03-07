package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Sale;
import cn.itcast.core.service.SaleService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Sale")
public class SaleController {
    @Reference
    private SaleService saleService;
    @RequestMapping("/getsale")
    public List<Sale> getsale(){
      return  saleService.getsale();
    }
}
