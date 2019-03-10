package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.GoodsService;
import cn.itcast.core.service.SeckillGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kill")
public class SecKillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;


    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids, String status) {
        try {
            seckillGoodsService.updateStatus(ids, status);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/search")
    public PageResult findPage(Integer page, Integer rows, @RequestBody SeckillGoods seckillGoods) {
        System.out.println("323");
        return seckillGoodsService.findage(page, rows, seckillGoods);
    }
}
