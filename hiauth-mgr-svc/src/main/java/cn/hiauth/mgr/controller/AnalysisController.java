package cn.hiauth.mgr.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

//@Api(value = "统计分析", tags = {"统计分析"})
@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

    /**
     * 首页演示数据
     */
//    @ApiOperation(value = "统计")
    @PostMapping("/dashboard")
    public Map dashboard() {

        // 1:
        List visitData = new ArrayList();
        Long beginDay = System.currentTimeMillis();
        Integer[] fakeY = {7, 5, 4, 2, 4, 7, 5, 6, 5, 9, 6, 3, 1, 5, 3, 6, 5};
        for (int i = 0; i < fakeY.length; i += 1) {
            int m = i;
            visitData.add(new HashMap<String, Object>(){{
                put("x",sdf.format(new Date(beginDay + 1000 * 60 * 60 * 24 * m)));
                put("y",fakeY[m]);
            }});
        }

        // 2:
        List visitData2 = new ArrayList();
        Integer[] fakeY2 = {1, 6, 4, 8, 3, 7, 2};
        for (int i = 0; i < fakeY2.length; i += 1) {
            int m = i;
            visitData2.add(new HashMap<String, Object>(){{
                put("x",sdf.format(new Date(beginDay + 1000 * 60 * 60 * 24 * m)));
                put("y",fakeY2[m]);
            }});
        }

        // 3:
        List salesData = new ArrayList();
        for (int i = 0; i < 12; i += 1) {
            int m = i;
            salesData.add(new HashMap<String, Object>(){{
                put("x", m +1+"月");
                put("y",(Math.floor(Math.random() * 1000) + 200));
            }});
        }

        // 4:
        List searchData = new ArrayList();
        for (int i = 0; i < 50; i += 1) {
            int m = i;
            searchData.add(new HashMap<String, Object>(){{
                put("index", m + 1);
                put("keyword","搜索关键词-" + m);
                put("count",Math.floor(Math.random() * 1000));
                put("range",Math.floor(Math.random() * 100));
                put("status",Math.floor((Math.random() * 10) % 2));
            }});
        }

        // 5:
        List offlineData = new ArrayList();
        for (int i = 0; i < 10; i += 1) {
            int m = i;
            offlineData.add(new HashMap<String, Object>(){{
                put("name", "Stores " + m);
                put("cvr",(Math.ceil(Math.random() * 9) / 10));
            }});
        }

        // 6:
        List offlineChartData = new ArrayList();
        for (int i = 0; i < 20; i += 1) {
            String date = sdf1.format(System.currentTimeMillis() + 1000 * 60 * 30 * i);
            offlineChartData.add(new HashMap<String, Object>(){{
                put("date", date);
                put("type", "客流量");
                put("value", (Math.floor(Math.random() * 100) + 10));
            }});
            offlineChartData.add(new HashMap<String, Object>(){{
                put("date", date);
                put("type", "支付笔数");
                put("value", (Math.floor(Math.random() * 100) + 10));
            }});
        }

        // 7:
        List salesTypeData = new ArrayList();
        salesTypeData.add(new HashMap<String, Object>(){{ put("x", "家用电器"); put("y", 4544); }});
        salesTypeData.add(new HashMap<String, Object>(){{ put("x", "食用酒水"); put("y", 3321); }});
        salesTypeData.add(new HashMap<String, Object>(){{ put("x", "个护健康"); put("y", 3113); }});
        salesTypeData.add(new HashMap<String, Object>(){{ put("x", "服饰箱包"); put("y", 2341); }});
        salesTypeData.add(new HashMap<String, Object>(){{ put("x", "母婴产品"); put("y", 1231); }});
        salesTypeData.add(new HashMap<String, Object>(){{ put("x", "其他"); put("y", 1231); }});

        // 8:
        List salesTypeDataOnline = new ArrayList();
        salesTypeDataOnline.add(new HashMap<String, Object>(){{ put("x", "家用电器"); put("y", 244); }});
        salesTypeDataOnline.add(new HashMap<String, Object>(){{ put("x", "食用酒水"); put("y", 321); }});
        salesTypeDataOnline.add(new HashMap<String, Object>(){{ put("x", "个护健康"); put("y", 311); }});
        salesTypeDataOnline.add(new HashMap<String, Object>(){{ put("x", "服饰箱包"); put("y", 41); }});
        salesTypeDataOnline.add(new HashMap<String, Object>(){{ put("x", "母婴产品"); put("y", 121); }});
        salesTypeDataOnline.add(new HashMap<String, Object>(){{ put("x", "其他"); put("y", 111); }});

        // 9:
        List salesTypeDataOffline = new ArrayList();
        salesTypeDataOffline.add(new HashMap<String, Object>(){{ put("x", "家用电器"); put("y", 99); }});
        salesTypeDataOffline.add(new HashMap<String, Object>(){{ put("x", "食用酒水"); put("y", 188); }});
        salesTypeDataOffline.add(new HashMap<String, Object>(){{ put("x", "个护健康"); put("y", 344); }});
        salesTypeDataOffline.add(new HashMap<String, Object>(){{ put("x", "服饰箱包"); put("y", 255); }});
        salesTypeDataOffline.add(new HashMap<String, Object>(){{ put("x", "其他"); put("y", 65); }});

        // 10:
        List radarData = new ArrayList();
        radarData.add(new HashMap<String, Object>(){{ put("name", "个人");put("label", "引用");put("value", 10); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "个人");put("label", "口碑");put("value", 8); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "个人");put("label", "产量");put("value", 4); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "个人");put("label", "贡献");put("value", 5); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "个人");put("label", "热度");put("value", 7); }});

        radarData.add(new HashMap<String, Object>(){{ put("name", "团队");put("label", "引用");put("value", 3); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "团队");put("label", "口碑");put("value", 9); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "团队");put("label", "产量");put("value", 6); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "团队");put("label", "贡献");put("value", 3); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "团队");put("label", "热度");put("value", 1); }});

        radarData.add(new HashMap<String, Object>(){{ put("name", "部门");put("label", "引用");put("value", 4); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "部门");put("label", "口碑");put("value", 1); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "部门");put("label", "产量");put("value", 6); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "部门");put("label", "贡献");put("value", 5); }});
        radarData.add(new HashMap<String, Object>(){{ put("name", "部门");put("label", "热度");put("value", 7); }});

        Map<String, Object> map = new HashMap<>();
        map.put("visitData", visitData);
        map.put("visitData2", visitData2);
        map.put("salesData", salesData);
        map.put("searchData", searchData);
        map.put("offlineData", offlineData);
        map.put("offlineChartData", offlineChartData);
        map.put("salesTypeData", salesTypeData);
        map.put("salesTypeDataOnline", salesTypeDataOnline);
        map.put("salesTypeDataOffline", salesTypeDataOffline);
        map.put("radarData", radarData);

        return map;
    }

}
