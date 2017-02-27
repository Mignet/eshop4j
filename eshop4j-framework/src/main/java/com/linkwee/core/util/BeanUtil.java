package com.linkwee.core.util;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;

public class BeanUtil
{
  public static Map<String, String> beanToMap(Object obj)
  {
    Map params = new HashMap(0);
    try {
      PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
      PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
      for (int i = 0; i < descriptors.length; i++) {
        String name = descriptors[i].getName();
        if (!StringUtils.equals(name, "class"))
          params.put(name, (String)propertyUtilsBean.getNestedProperty(obj, name));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return params;
  }

  public static TreeMap<String, String> beanToSortMap(Object obj)
  {
    TreeMap params = new TreeMap();
    try {
      PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
      PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
      for (int i = 0; i < descriptors.length; i++) {
        String name = descriptors[i].getName();
        if (!StringUtils.equals(name, "class")) {
          Object o = propertyUtilsBean.getNestedProperty(obj, name);
          if (o != null)
            params.put(name, o.toString());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return params;
  }

  public static Map<String, String> mapToSortMap(Map<String, String> map)
  {
    Map tree = new TreeMap<String, String>();

    Set<String> keys = map.keySet();
    for (String key : keys) {
      tree.put(key, (String)map.get(key));
    }

    return tree;
  }

  public static void transMap2Bean(Map<String, Object> map, Object obj)
  {
    try
    {
      Iterator names = map.keySet().iterator();
      while (names.hasNext()) {
        String name = (String)names.next();
        if (PropertyUtils.isWriteable(obj, name)) {
          Object value = map.get(name);
          if (value != null) {
            PropertyUtils.setSimpleProperty(obj, name, value);
          }

        }

      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
