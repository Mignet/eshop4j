package com.eshop4j.test.sysprops;

import java.util.Properties;
import java.util.Scanner;
 
public class SysPropsTool {
  public static final String QUIT = "/Q";
  public static final String QUIT_ = "/QUIT";
  public static final String HELP = "/?";
  public static final String HELP_ = "/H";
  public static final String HELP__ = "/HELP";
  public static final String LIST = "/LS";
  public static final String LIST_ = "/LIST";
  public static final String LISTALL = "/ALL";
 
  public static final String PROPS = "props >>>";
 
  static Properties props = System.getProperties();
  public static void main (String[] args) {
    //props = System.getProperties();
    usageInfo();
 
    Scanner sc = new Scanner(System.in);
 
 
    while (true) {
      System.out.print(PROPS);
      try {
        String line = sc.nextLine();
 
        if (line == null || line.length() < 1) {
          continue;
        }
        String preStic = line.trim().toUpperCase();
 
        if (QUIT.equals(preStic) || QUIT_.equals(preStic)) {
          break;
        }
 
        if (HELP.equals(preStic) || HELP_.equals(preStic) || HELP__.equals(preStic)) {
          usageInfo();
          continue;
        }
 
        boolean isList = LIST.equals(preStic) || LIST_.equals(preStic) || LISTALL.equals(preStic);
        if (isList) {
          showAll();
          System.out.println("-- listed over --");
        }else {
          showIndivl(line);
        }
 
 
      }catch (Exception ex) {
 
        usageInfo();
      }
    }
    sc.close();
 
  }
 
  static void showAll () {
    if (props != null) {
      props.list(System.out);
    }
  }
 
  static void showIndivl (String key) {
    String value = props.getProperty(key, "[NAN]");
    StringBuilder info = new StringBuilder(key);
    info.append("=").append(value);
 
    System.out.println(info);
  }
 
  public static String get (String key) {
    return props.getProperty(key);
  }
 
  public static Properties set (String key, String value) {
    props.setProperty(key, value);
    return props;
  }
 
  static void usageInfo () {
    System.out.println("[Usage]: \n\t [/q|/quit]:\t to quit \n\t [/ls|/list|/all]:\t to list all props \n\t {key}:\t get the key-value \n\t [/h|/?|/help]:\t for help");
  }
}
