package com.linkwee.xoss.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


/**
 * 金额工具类
 *
 * @version 1.0.0
 * @see BigDecimalUtil
 */
public class BigDecimalUtil {

	/**
	 * 判断 x,y是否相等
	 *
	 * @param x 数字
	 * @param y 数字
	 * @return true :相等 false:不相等
	 */
	public static boolean isEqual(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}
		return new BigDecimal(x.toString()).compareTo(new BigDecimal(y.toString())) == 0;
	}

	/**
	 * x,y相加
	 *
	 * @param x 数字
	 * @param y 数字
	 * @return 相加值
	 */
	public static BigDecimal add(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());
		return xBigDecimal.add(yBigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

    /**
     * 多个金额相加
     * @param nums
     * @return
     */
    public static BigDecimal add(BigDecimal... nums) {
        BigDecimal addCount = new BigDecimal(0);

        for(BigDecimal num:nums) {
            addCount = addCount.add(num);
        }

        return addCount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

	/**
	 * 判断 x,y相除 x/y
	 *
	 * @param x 数字
	 * @param y 数字
	 * @return true :相等 false:不相等
	 */
	public static BigDecimal divide(
			Number x, Number y, int scale, RoundingMode roundingMode) {

		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		return xBigDecimal.divide(yBigDecimal, scale, roundingMode).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 判断 x,y相除 x/y
	 *
	 * @param x 数字
	 * @param y 数字
	 * @return 除以结果
	 */
	public static BigDecimal divide(Number x, Number y) {
		return divide(x, y, 2, RoundingMode.DOWN);
	}

	/**
	 * 两数相乘
	 *
	 * @param x 乘数
	 * @param y 被乘数
	 * @return 结果
	 */
	public static BigDecimal multiply(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		return xBigDecimal.multiply(yBigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);

	}

	/**
	 * 计算精度
	 *
	 * @param x            数字
	 * @param scale        位数
	 * @param roundingMode 舍入模式
	 * @return 结果
	 */
	public static BigDecimal scale(Number x, int scale, RoundingMode roundingMode) {
		if (x == null) {
			x = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());

		return xBigDecimal.setScale(scale, roundingMode);

	}

	/**
	 * 格式化进度显示
	 *
	 * @param x 数字
	 * @return 2位小数显示
	 */
	public static BigDecimal scale(Number x) {
		return scale(x, 2, RoundingMode.DOWN);

	}

	/**
	 * 两数相减
	 *
	 * @param x 减数
	 * @param y 被减数
	 * @return 结果
	 */
	public static BigDecimal subtract(Number x, Number y) {
		if (x == null) {
			x = 0;
		}

		if (y == null) {
			y = 0;
		}

		BigDecimal xBigDecimal = new BigDecimal(x.toString());
		BigDecimal yBigDecimal = new BigDecimal(y.toString());

		return scale(xBigDecimal.subtract(yBigDecimal));
	}

	/**
	 * 随机金额
	 */
	public static final Random random = new Random();
	public static BigDecimal getRandomMoney(double max) {
		double num;
		do {
			num = random.nextDouble();
		}
		while (num == 0);

		BigDecimal money = new BigDecimal(num * max);
		return scale(money);
	}

    /**
     * 大小比较
     * @param src
     * @param compareObj
     * @return
     */
    public static boolean greaterTo(BigDecimal src, BigDecimal compareObj) {

        return src.compareTo(compareObj) > 0 ? true:false;
    }


    /** 截断位数 */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 将double类型装换成long类型  （高精度）  
     * @param value  值
     * @param multiple  倍数
     * @param accuracy 小数点后精度位数
     * @return
     */
    public static long changeDouble2Long(double value,int multiple,int accuracy){
    	return new BigDecimal(value).setScale(accuracy, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(multiple)).longValue();
    }
}
