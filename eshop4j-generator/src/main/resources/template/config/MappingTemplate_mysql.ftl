<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${dao.fullName}">
	<!-- Result Map-->
	<resultMap  type="${entity.fullName}" id="BaseResultMap">
	<#list vo.attributes as attribute>
		<result column="${attribute.column.columnName}" property="${attribute.attributeName}" jdbcType="${attribute.column.jdbcType}"/>
	</#list>
	</resultMap>
	
  <sql id="Base_Column_List">
    <trim suffixOverrides=",">
	  	<#list vo.attributes as attribute>
	    	${attribute.column.sqlName},
	    </#list>
    </trim>
  </sql>
  
 <sql id="Base_Column_List_T">
    <trim suffixOverrides=",">
	  	<#list vo.attributes as attribute>
	    	t.${attribute.column.sqlName},
	    </#list>
    </trim>
  </sql>  
  
  <sql id="Base_Condition">
	<#list vo.attributes as attribute>
		<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
			and ${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}}
		</if>
	</#list>
  </sql>
  
  <select id="selectOneByCondition" resultMap="BaseResultMap"  parameterType="${entity.fullName}">
		select <include refid="Base_Column_List" /> from ${vo.table.sqlName} 
		where 1=1 <include refid="Base_Condition" />
  </select>
  
  <select id="selectByCondition" resultMap="BaseResultMap"  parameterType="${entity.fullName}">
		select <include refid="Base_Column_List" /> from ${vo.table.sqlName} 
		where 1=1 <include refid="Base_Condition" />
  </select>

  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long" >
    select 
    <include refid="Base_Column_List" />
    from ${vo.table.sqlName}
    where <#if vo.table.primaryKey??> ${vo.table.primaryKey.sqlName} = #${r'{'}0}</#if>
  </select>
  
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
    delete from ${vo.table.sqlName}
    where ${vo.table.primaryKey.sqlName} = #${r'{'}0}
  </delete>

  <insert id="insert" parameterType="${entity.fullName}" >
    INSERT INTO ${vo.table.sqlName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list vo.attributes as attribute>
			 	<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
					${attribute.column.sqlName},
				</if>
			</#list>
		</trim>
		<trim prefix="VALUES(" suffix=")" suffixOverrides=",">
		     <#list vo.attributes as attribute>
			 	<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
					 	#${r'{'}${attribute.attributeName}},
				</if>
			</#list>
		</trim>
  </insert>
  
  <insert id="insertSelective" parameterType="${entity.fullName}" <#if vo.primaryKey ??>useGeneratedKeys="true" keyProperty="${vo.primaryKey.attributeName}"</#if> >
		INSERT INTO ${vo.table.sqlName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list vo.attributes as attribute>
			 	<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
					${attribute.column.sqlName},
				</if>
			</#list>
		</trim>
		<trim prefix="VALUES(" suffix=")" suffixOverrides=",">
		     <#list vo.attributes as attribute>
			 	<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
					 	#${r'{'}${attribute.attributeName}},
				</if>
			</#list>
		</trim>
	</insert>
  
  <update id="updateByPrimaryKeySelective" parameterType="${entity.fullName}" >
  		UPDATE ${vo.table.sqlName}
		<set>
			<trim suffixOverrides=",">
				<#list vo.attributes as attribute>
				   <#if !attribute.column.primaryKey>
					<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
						${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}},
					</if>
					</#if>
				</#list>
			</trim>
		</set>
		<where>
			<#list vo.attributes as attribute><#if attribute.column.primaryKey> ${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}}</#if></#list>
		</where>
	</update>
  
	<update id="updateByPrimaryKey" parameterType="${entity.fullName}" >
	    UPDATE ${vo.table.sqlName}
	   <set>
			<trim suffixOverrides=",">
				<#list vo.attributes as attribute>
				   <#if !attribute.column.primaryKey>
					<if test=" null != ${attribute.attributeName} <#if attribute.fullName == "java.lang.String">and ''!= ${attribute.attributeName}  </#if>">
						${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}},
					</if>
					</#if>
				</#list>
			</trim>
		</set>
		<where>
			${vo.table.primaryKey.sqlName} = #${r'{'}0}
		</where>
	  </update>

	<!-- 分页条件查询 -->
    <select id="selectBySearchInfo" resultMap="BaseResultMap" parameterType="com.linkwee.core.datatable.DataTable">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${vo.table.sqlName}
        <where> 
        <if test="dt.search!=null">  
                name LIKE CONCAT('%','${r'${dt.search.value}'}','%' )  
         </if>  
         <if test="dt.order!=null">  
               <foreach collection="dt.order" index="index" item="item"
                    open="order by" separator="," close=" ">
                   ${r' ${item.name} ${item.dir} '}
                </foreach>
         </if>  
        </where>
    </select>

</mapper>