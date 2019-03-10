package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.SeckillGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kill")
public class SecKillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;
    @RequestMapping("/add")
    public Result add(@RequestBody SeckillGoods seckillGoods) {
        System.out.println(seckillGoods.getSmallPic());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillGoods.setSellerId(userName);
        System.out.println(seckillGoods.toString());
        try {
            seckillGoodsService.add(seckillGoods);
            return new Result(true, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");

        }
    }

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
