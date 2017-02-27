package com.linkwee.web.controller;

import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.result.Result;
import com.linkwee.web.model.Permission;
import com.linkwee.web.service.PermissionService;
import com.linkwee.xoss.rbac.PermissionSign;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限管理控制器<br>
 * 完全的Restful API
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/permissions")
public class PermissionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);
	
    @Resource
    private PermissionService permissionService;

    /**
     * select all <br>
     * Restful
     * @return
     */
    @RequestMapping(value="",   method=RequestMethod.GET)  
    @ResponseBody
    public Page<Permission> getPermissions(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize) {
    	Page<Permission> page = new Page<Permission>(pageNo,pageSize);
    	List<Permission> users = permissionService.selectList(page);  
    	LOGGER.debug("PermissionService.selectList() :"+users);
        return page;
    }

    /**
     * @Author Libin
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/json",method = RequestMethod.GET)
    @ResponseBody
    public List<Permission> findPermissionAll() throws Exception{
        return permissionService.selectListAll();
    }
    
    /**
     * 基于权限标识的权限控制案例<br>
     * POST Restful
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.SYS_PERMISSION_ALL)
    public Result create(Permission item) {
    		item.setId(null);
	    	int i = permissionService.insert(item);
	    	if(i==1){
	    		return new Result(true,201,"新增成功!");
	    	}else{
	    		return new Result(false,500,"新增失败");
	    	}
    }
    
    /**
     * 基于权限标识的权限控制案例<br>
     * PUT Restful
     */
    @RequestMapping(value = "/{pid}", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.SYS_PERMISSION_ALL)
    public Result update(Permission item) {
    		int i = permissionService.update(item);
    		if(i==1){
    			return new Result(true,200,"更新成功!");
    		}else{
    			return new Result(false,500,"更新失败");
    		}
    }
    
    /**
     *  DELETE Restful
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.SYS_PERMISSION_ALL)
    public Result delete(@PathVariable("id") String id) {
    	int i = permissionService.delete(Integer.valueOf(id));
    	if(i==1){
    		return new Result(true,"删除成功!");
    	}else{
    		return new Result(false,500,"删除失败");
    	}
    }
    /**
     *  GET Restful
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Permission get(@PathVariable("id") String id) {
    	return permissionService.selectById(Integer.valueOf(id));
    }
    
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public boolean canUsed(@RequestParam("permissionName") String permissionName) {
    	List<Permission> u =  permissionService.selectByName(permissionName);
    	if(u!=null&&u.size()>=1){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
     * dispatcher view
     * @return
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)  
    @RequiresPermissions(value = PermissionSign.SYS_PERMISSION_ALL)
    public String permissions() {
    	return "sys/permission-list";
    }

}
