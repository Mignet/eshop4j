package ${entity.strPackage};

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
<#list vo.attrFullNames as attrFullName>
 import ${attrFullName};
</#list>
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class ${entity.shortName} implements Serializable {
	
	private static final long serialVersionUID = ${generate.serialVersionUID};
	
<#list vo.attributes as attribute>
<#if attribute.comment??>
    /**
     *${attribute.comment}
     */
</#if>     
<#if "Date"==attribute.shortName>
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
</#if>     
	private ${attribute.shortName} ${attribute.attributeName};
	
</#list>


<#list vo.attributes as attribute>
	public void ${attribute.setMethodName}(${attribute.shortName} ${attribute.attributeName}){
		this.${attribute.attributeName} = ${attribute.attributeName};
	}
	
	public ${attribute.shortName} ${attribute.getMethodName}(){
		return ${attribute.attributeName};
	}
	
</#list>

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

