package com.eshop4j.xoss.util;

import java.util.UUID;


public class GenerateNumberUtils {
	
  public static String generateKey(){
        return UUID.randomUUID().toString().replaceAll("-","");
   }
}
