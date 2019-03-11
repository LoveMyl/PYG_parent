package cn.itcast.core.controller;

import cn.itcast.core.common.ExcelUtil;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.GoodsDesc;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.pojo.user.User;
import cn.itcast.core.service.POIService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/download")
public class POIController {
    @Reference
    private POIService poiService;

    @RequestMapping("/downloaduser")
    public void download(HttpServletResponse res) throws Exception {
        List<OrderItem> orderList = poiService.downloadorder();
        if (orderList != null) {
            String sheetName = "商品统计表";
            String titleName = "商品数据统计表";
            String fileName = "OrderItem" + String.valueOf(System.currentTimeMillis());
            //Id,金额,支付类型,支付状态,创建时间,用户名,收货地址,收货人,卖家名
            int[] columnWidth = {25, 20, 35, 30, 40, 10, 10, 40, 10};
            String[] columnName = {"id", "SKU", "SPU", "订单id", "商品标题", "单价", "数量", "图片", "商家名称"};
            int columnNumber = 9;
            String[][] dataList = new String[orderList.size()][columnName.length];
            for (int i = 0; i < orderList.size(); i++) {
                OrderItem orderItem = orderList.get(i);
                dataList[i][0] = String.valueOf(orderItem.getId());
                dataList[i][1] = String.valueOf(orderItem.getItemId());
                dataList[i][2] = String.valueOf(orderItem.getGoodsId());
                dataList[i][3] = String.valueOf(orderItem.getOrderId());
                dataList[i][4] = String.valueOf(orderItem.getTitle());
                dataList[i][5] = String.valueOf(orderItem.getPrice());
                dataList[i][6] = String.valueOf(orderItem.getNum());
                dataList[i][7] = String.valueOf(orderItem.getPicPath());
                dataList[i][8] = String.valueOf(orderItem.getSellerId());
            }
            try {
                ExcelUtil.ExportWithResponse(sheetName, titleName, fileName, columnNumber, columnWidth, columnName, dataList, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/downloadyonghu")
    public void downloadyong(HttpServletResponse res) throws Exception {
        List<User> userList = poiService.download();
        if (userList != null) {
            String sheetName = "用户统计表";
            String titleName = "注册用户统计表";
            String fileName = "User" + String.valueOf(System.currentTimeMillis());
            //Id,金额,支付类型,支付状态,创建时间,用户名,收货地址,收货人,卖家名
            int[] columnWidth = {5, 20, 20, 20, 10, 20, 10, 10, 10
                    , 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            String[] columnName = {"序号", "名字", "密码", "电话", "邮箱", "注册时间", "修改时间", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9",
                    "s10", "11", "12", "13", "14", "s15"};
            int columnNumber = 22;
            String[][] dataList = new String[userList.size()][columnName.length];
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                dataList[i][0] = String.valueOf(user.getId());
                dataList[i][1] = String.valueOf(user.getUsername());
                dataList[i][2] = String.valueOf(user.getPassword());
                dataList[i][3] = String.valueOf(user.getPhone());
                dataList[i][4] = String.valueOf(user.getEmail());
                dataList[i][5] = String.valueOf(user.getCreated());
                dataList[i][6] = String.valueOf(user.getUpdated());
                dataList[i][7] = String.valueOf(user.getSourceType());
                dataList[i][8] = String.valueOf(user.getNickName());
                dataList[i][9] = String.valueOf(user.getName());
                dataList[i][10] = String.valueOf(user.getStatus());
                dataList[i][11] = String.valueOf(user.getHeadPic());
                dataList[i][12] = String.valueOf(user.getQq());
                dataList[i][13] = String.valueOf(user.getAccountBalance());
                dataList[i][14] = String.valueOf(user.getIsMobileCheck());
                dataList[i][15] = String.valueOf(user.getIsEmailCheck());
                dataList[i][16] = String.valueOf(user.getSex());
                dataList[i][17] = String.valueOf(user.getUserLevel());
                dataList[i][18] = String.valueOf(user.getPoints());
                dataList[i][19] = String.valueOf(user.getExperienceValue());
                dataList[i][20] = String.valueOf(user.getBirthday());
                dataList[i][21] = String.valueOf(user.getLastLoginTime());
            }

            try {
                ExcelUtil.ExportWithResponse(sheetName, titleName, fileName, columnNumber, columnWidth, columnName, dataList, res);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    @RequestMapping("/downloadorder")
    public void downloadorder(HttpServletResponse res) throws Exception {
        List<GoodsDesc> goodsDescList = poiService.downloadgoodid();
        if (goodsDescList != null) {
            String sheetName = "订单统计表";
            String titleName = "订单详情统计表";
            String fileName = "goods" + String.valueOf(System.currentTimeMillis());
            //Id,金额,支付类型,支付状态,创建时间,用户名,收货地址,收货人,卖家名
            int[] columnWidth = {10, 40, 60, 60, 60, 20, 20};
            String[] columnName = {"序号", "商品描述", "商品规格", "属性", "图片地址", "包装列表", "售后服务"};
            int columnNumber = 7;
            String[][] dataList = new String[goodsDescList.size()][columnName.length];
            for (int i = 0; i < goodsDescList.size(); i++) {
                GoodsDesc goodsDesc = goodsDescList.get(i);
                dataList[i][0] = String.valueOf(goodsDesc.getGoodsId());
                dataList[i][1] = String.valueOf(goodsDesc.getIntroduction());
                dataList[i][2] = String.valueOf(goodsDesc.getSpecificationItems());
                dataList[i][3] = String.valueOf(goodsDesc.getCustomAttributeItems());
                dataList[i][4] = String.valueOf(goodsDesc.getItemImages());
                dataList[i][5] = String.valueOf(goodsDesc.getPackageList());
                dataList[i][6] = String.valueOf(goodsDesc.getSaleService());
            }
            try {
                ExcelUtil.ExportWithResponse(sheetName, titleName, fileName, columnNumber, columnWidth, columnName, dataList, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/importUsers")
    @ResponseBody
    public String importUsers(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        try {
            List<Brand> brandList=new ArrayList<Brand>();
            Workbook workBook = ExcelUtil.getWorkBook(file);
            List<String[]> strings = ExcelUtil.readExcelGetList(workBook);
            for (String[] string : strings) {
                Brand brand = new Brand();
                brand.setName(string[0]);
                 brand.setFirstChar(string[1]);
                 brandList.add(brand);
            }
            poiService.addbrand(brandList);
            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "失败";
        }
    }
    @RequestMapping("/importspe")
    @ResponseBody
    public String importspe(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        try {
            List<Specification> specList=new ArrayList<Specification>();
            Workbook workBook = ExcelUtil.getWorkBook(file);
            List<String[]> strings = ExcelUtil.readExcelGetList(workBook);
            for (String[] string : strings) {
                Specification spe = new Specification();
                spe.setSpecName(string[0]);
                specList.add(spe);
            }
            poiService.addspe(specList);
            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "失败";
        }
    }
    @RequestMapping("/importtem")
    @ResponseBody
    public String importtem(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        try {
            List<TypeTemplate> specList = new ArrayList<TypeTemplate>();
            Workbook workBook = ExcelUtil.getWorkBook(file);
            List<String[]> strings = ExcelUtil.readExcelGetList(workBook);
            for (String[] string : strings) {
                TypeTemplate spe = new TypeTemplate();
                spe.setName(string[0]);
                spe.setSpecIds(string[1]);
                spe.setBrandIds(string[2]);
                spe.setCustomAttributeItems(string[3]);
                specList.add(spe);
            }
            poiService.addtem(specList);
            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "失败";
        }
    }

}