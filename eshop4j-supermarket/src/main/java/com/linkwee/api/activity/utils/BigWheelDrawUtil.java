package com.linkwee.api.activity.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.activity.BaseLottery;

public class BigWheelDrawUtil {
	/**
     * 给转盘的每个奖项赋初始值
     * @return
     */
    private final static List<BaseLottery> initDrawList = new ArrayList<BaseLottery>() {{
        add(new BaseLottery(1, "8元现金", 980));
        add(new BaseLottery(2, "500M流量包", 7));
        add(new BaseLottery(3, "取暖器", 1));
        add(new BaseLottery(4, "20元现金", 10));
        add(new BaseLottery(5, "烘干器", 2));
    }};

    /**
     * 生成奖项
     * @return
     */
    public static BaseLottery generateAward() {
        long result = randomnum(1, 1000);
        int line = 0;
        int temp = 0;
        BaseLottery returnobj = null;
        for (int i = 0; i < initDrawList.size(); i++) {
        	BaseLottery obj2 = initDrawList.get(i);
            int c = obj2.getVariable();
            temp = temp + c;
            line = 1000 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }

    // 获取2个值之间的随机数
    private static long randomnum(int smin, int smax){
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }

    public static void main(String[] args) {
    	int count = 0;
    	for(int i = 0; i < 1000; i++){
    		BaseLottery temp = generateAward();
    		if(temp.getId() == 2 || temp.getId() == 3 || temp.getId() == 4 || temp.getId() == 5){
    			System.out.println(JSON.toJSONString(temp));
    		}
    		if(temp.getId() == 1){
    			count++;
    		}
    	}
        System.out.println(count);
    }
}
