package com.linkwee.xoss.util;

import java.util.List;

public class InterceptUtility {
	/**
	 * 获取分段数
	 * @param totalCounts
	 * @return
	 */
	public static int getSubsectionCount(int totalCounts,int limitCount){
		 int count=0;
		 if (totalCounts % limitCount == 0) {
			count =  (totalCounts / limitCount);
		} else {
			count =  (totalCounts / limitCount) + 1;
		}
		return count;
	}
	
	/**
	 * 分段 redpacketInsertNumer 个为一段
	 * @param lists
	 * @param listss
	 */
	public static <T> void  subsection(List<T> lists,List<List<T>> listss,int limitCount){
		 int totalCounts = lists.size();
		 int count =  getSubsectionCount(totalCounts,limitCount);
		 for (int i = 0; i < count; i++) {
			int from = i * limitCount;
			int to = from + limitCount;
			if(to > totalCounts){
				to = totalCounts;
			}
			listss.add(lists.subList(from, to));
		}
	}
}
