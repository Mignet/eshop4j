package com.linkwee.plugins.plfk.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class PaseXMLUtil {
	
	
	//具体解析XML方法
	@SuppressWarnings("unchecked")
	public static HashMap<String,String> paseXML(InputStream is){
		SAXBuilder sb=new SAXBuilder();
		Document doc=null;
		try {
			doc=sb.build(is);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Element root=doc.getRootElement();
		Element child=null;
		
		HashMap<String,String> childHM=new HashMap<String,String>();
		
		for (Iterator<Element> childs= root.getChildren().iterator();childs.hasNext();) {
			child=childs.next();
			childHM.put(child.getName(), child.getValue());
		}
		return childHM;
	}
}
