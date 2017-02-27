package com.linkwee.core.util;
import com.linkwee.core.base.BaseEnum;
import com.linkwee.core.base.KeyValueEnum;
import com.linkwee.core.base.KvmEnum;

/**
 * 
 * @描述：枚举工具类
 *
 * @author Bob
 * @时间  2015年8月5日上午10:10:57
 *
 */
public class EnumUtils {
	
	/**
	 * 根据code获取具体枚举
	 * @param code 
	 * @param enums 枚举
	 * @return
	 */
	public static BaseEnum  getEnumByCode(int code, Enum<? extends BaseEnum>[] enums) {
		for (Enum<? extends BaseEnum> e : enums) {
			BaseEnum temp = (BaseEnum) e;
			if (temp.getCode()==code) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * 根据code获取message
	 * @param code 
	 * @param enums 枚举
	 * @return
	 */
	public static String  getMessageByCode(int code, Enum<? extends BaseEnum>[] enums) {
		BaseEnum temp = getEnumByCode(code,enums);
		return temp==null?"":temp.getMessage();
	}
	
	/**
	 *  根据message获取具体枚举
	 * @param message
	 * @param enums
	 * @return
	 */
	public static BaseEnum  getEnumByMessage(String message, Enum<? extends BaseEnum>[] enums) {
		for (Enum<? extends BaseEnum> e : enums) {
			BaseEnum temp = (BaseEnum) e;
			if (temp.getMessage().equals(message)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 *  根据message获取具体枚举
	 * @param message
	 * @param enums
	 * @return
	 */
	public static int getCodeByMessage(String message, Enum<? extends BaseEnum>[] enums) {
		BaseEnum temp = getEnumByMessage(message,enums);
		return temp==null?0:temp.getCode();
	}
	
	/**
	 * 根据key获取枚举值
	 * @param key  键
	 * @param enums 枚举
	 * @return
	 */
	public static KeyValueEnum  getEnumByKey(int key, Enum<? extends KeyValueEnum>[] enums) {
		for (Enum<? extends KeyValueEnum> e : enums) {
			KeyValueEnum temp = (KeyValueEnum) e;
			if (temp.getKey()==key) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * 根据key获取value
	 * @param key 键
	 * @param enums 枚举
	 * @return
	 */
	public static String  getValueByKey(int key, Enum<? extends KeyValueEnum>[] enums) {
		KeyValueEnum temp = getEnumByKey(key,enums);
		return temp==null?"":temp.getValue();
	}
	
	/**
	 * 根据key获取value 增加key为null的判断
	 * @param key 键   
	 * @param enums 枚举
	 * @return
	 */
	public static String  getValueByKeyNull(Integer key, Enum<? extends KeyValueEnum>[] enums) {
		if(null == key){
			return "";
		} else {
			KeyValueEnum temp = getEnumByKey(key,enums);
			return temp==null?"":temp.getValue();
		}
	}
	
	/**
	 *  根据value获取具体枚举
	 * @param value 值
	 * @param enums
	 * @return
	 */
	public static KeyValueEnum  getEnumByValue(String value, Enum<? extends KeyValueEnum>[] enums) {
		for (Enum<? extends KeyValueEnum> e : enums) {
			KeyValueEnum temp = (KeyValueEnum) e;
			if (temp.getValue().equals(value)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 *  根据value获取枚举键
	 * @param value 值
	 * @param enums
	 * @return
	 */
	public static int getKeyByValue(String value, Enum<? extends KeyValueEnum>[] enums) {
		KeyValueEnum temp = getEnumByValue(value,enums);
		return temp==null?0:temp.getKey();
	}
	
	/**
	 * 根据key获取枚举值
	 * @param key  键
	 * @param enums 枚举
	 * @return
	 */
	public static KvmEnum  getKvmEnumByKey(int key, Enum<? extends KvmEnum>[] enums) {
		for (Enum<? extends KvmEnum> e : enums) {
			KvmEnum temp = (KvmEnum) e;
			if (temp.getKey()==key) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * 根据key获取value
	 * @param key 键
	 * @param enums 枚举
	 * @return
	 */
	public static String  getValueByKvmEnumKey(int key, Enum<? extends KvmEnum>[] enums) {
		KvmEnum temp = getKvmEnumByKey(key,enums);
		return temp==null?"":temp.getValue();
	}
	
	/**
	 * 根据key获取msg
	 * @param key 键
	 * @param enums 枚举
	 * @return
	 */
	public static String  getMsgByKvmEnumKey(int key, Enum<? extends KvmEnum>[] enums) {
		KvmEnum temp = getKvmEnumByKey(key,enums);
		return temp==null?"":temp.getMsg();
	}
	
	/**
	 *  根据value获取具体枚举
	 * @param value 值
	 * @param enums
	 * @return
	 */
	public static KvmEnum  getKvmEnumByValue(String value, Enum<? extends KvmEnum>[] enums) {
		for (Enum<? extends KvmEnum> e : enums) {
			KvmEnum temp = (KvmEnum) e;
			if (temp.getValue().equals(value)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 *  根据value获取枚举键
	 * @param value 值
	 * @param enums
	 * @return
	 */
	public static int getKeyByKvmEnumValue(String value, Enum<? extends KvmEnum>[] enums) {
		KvmEnum temp = getKvmEnumByValue(value,enums);
		return temp==null?0:temp.getKey();
	}
	
	/**
	 *  根据value获取枚msg
	 * @param value 值
	 * @param enums
	 * @return
	 */
	public static String getMsgByKvmEnumValue(String value, Enum<? extends KvmEnum>[] enums) {
		KvmEnum temp = getKvmEnumByValue(value,enums);
		return temp==null?"":temp.getMsg();
	}
	
	
	
	/**
	 *  根据msg获取具体枚举
	 * @param msg 值
	 * @param enums
	 * @return
	 */
	public static KvmEnum  getKvmEnumByMsg(String msg, Enum<? extends KvmEnum>[] enums) {
		for (Enum<? extends KvmEnum> e : enums) {
			KvmEnum temp = (KvmEnum) e;
			if (temp.getMsg().equals(msg)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 *  根据value获取枚举键
	 * @param msg 值
	 * @param enums
	 * @return
	 */
	public static int getKeyByKvmEnumMsg(String msg, Enum<? extends KvmEnum>[] enums) {
		KvmEnum temp = getKvmEnumByMsg(msg,enums);
		return temp==null?0:temp.getKey();
	}
	
	/**
	 *  根据value获取枚举键
	 * @param msg 值
	 * @param enums
	 * @return
	 */
	public static String getValueByKvmEnumMsg(String msg, Enum<? extends KvmEnum>[] enums) {
		KvmEnum temp = getKvmEnumByMsg(msg,enums);
		return temp==null?"":temp.getValue();
	}
}
