package com.linkwee.xoss.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mignet on 2016/6/15.
 *
 * @Author Libin
 * @Date 2016/6/15
 */
public class HtmlFilterUtil {

    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    public static String filterHtml(String html){
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }

    public static void main(String[] args) throws Exception{
        System.out.println(filterHtml("<p style=\"width:10px;\">\n" +
                "    <strong>它“跌”就算了，还“跌”得跟我有超大关系！ </strong>\n" +
                "</p>"));
    }
}
