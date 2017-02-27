package com.eshop4j.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ImmutableMap;
import com.eshop4j.core.datatable.DataInfo;
import com.eshop4j.core.datatable.DataResult;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.ErrorField;
import com.eshop4j.core.result.Result;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.web.model.Permission;
import com.eshop4j.web.model.Role;
import com.eshop4j.web.service.PermissionService;
import com.eshop4j.web.service.RoleService;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.rbac.PermissionSign;
import com.eshop4j.xoss.util.RequestLogging;

/**
 * 角色控制器
 *
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/role")
@RequestLogging("角色控制器")
public class RoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;
    
    @Resource
    private PermissionService permissionService;

	/**
	 * 日期转换
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    /**
     * 基于角色 比如拥有admin角色，才可以查看角色列表.
     */
    @RequestMapping(value="",   method=RequestMethod.GET)
    @RequiresPermissions(value = PermissionSign.SYS_ROLE_CREATE)
    public String roles(Model model) {
    	return "sys/role-list";
    }

    /**
     * 翻页<br>
     * 针对前端组件获取后端的情形
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
	public DataTableReturn getRoles(@RequestParam String  _dt_json) {
		LOGGER.debug("role list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = roleService.selectByDatatables(dataTable);
		return tableReturn;
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = PermissionSign.SYS_ROLE_CREATE)
	public Object save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,Role> map =  (Map<String, Role>) df.getData();
    	DataResult dr = new DataResult();
    	List<Role> datas = new ArrayList<Role>();
    	List<ErrorField> errors = new ArrayList<ErrorField>();
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();    
        Validator validator = factory.getValidator();    
    	try {
			if(DataInfo.ACTION_CREATE.equals(df.getAction())){
				for (String key : map.keySet()) {
					Role r = new Role();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<Role>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<Role> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.roleService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					Role r = new Role();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<Role>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<Role> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.roleService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.roleService.delete(Integer.parseInt(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
    /**
     *  获取角色权限列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/permission", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.SYS_ROLE_CREATE)
    public Map<String, List<Permission>> getPermission(@PathVariable("id") String id) {
    	List<Permission> permissions = permissionService.selectListAll();
    	List<Permission> permissionList = permissionService.selectPermissionsByRoleId(Integer.parseInt(id));
    	return ImmutableMap.of("permissions",permissions,"permissionList",permissionList);
    }
    
    @RequestMapping(value = "/{id}/permission", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.SYS_ROLE_CREATE)
    public Result updatePermissions(@PathVariable("id") String id,String permissions) {
    	if(permissions==null){
    		permissions = "";
    	}
    	boolean flag = permissionService.updateRolePermissions(id,permissions.split(","));
    	if(flag){
    		return new Result(true,"更新成功!");
    	}else{
    		return new Result(false,500,"更新失败");
    	}
    }

}
