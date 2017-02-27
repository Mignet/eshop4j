package com.linkwee.core.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 * 为JavaClass劫持java.lang.System提供支持
 * 除了out和err外，其余的都直接转发给System处理
 * 
 * @author Mignet
 */
public class HackSystem {

    public static final InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    public static final PrintStream out = new PrintStream(buffer);

    public static final PrintStream err = out;

    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager s) {
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    // 下面所有的方法都与java.lang.System的名称一样
    // 实现都是字节转调System的对应方法
    public static Properties getProperties() {
    	return System.getProperties();
    }
    public static String lineSeparator() {
        return System.lineSeparator();
    }
    public static void setProperties(Properties props) {
    	System.setProperties(props);
    }
    public static String getProperty(String key) {
    	return System.getProperty(key);
    }
    public static String getProperty(String key, String def) {
    	return System.getProperty(key,def);
    }
    public static String setProperty(String key, String value) {
    	return System.setProperty(key, value);
    }
    public static String clearProperty(String key) {
    	return System.clearProperty(key);
    }
    public static String getenv(String name) {
    	return System.getenv(name);
    }
    public static java.util.Map<String,String> getenv() {
    	return System.getenv();
    }
}

