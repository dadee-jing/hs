package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.DeviceDynamicCheck;
import com.ruoyi.yaogan.service.IDeviceDynamicCheckService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 遥测设备动态检查过程数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/deviceDynamicCheck")
public class DeviceDynamicCheckController extends BaseController
{
    private String prefix = "yaogan/deviceDynamicCheck";
	
	@Autowired
	private IDeviceDynamicCheckService deviceDynamicCheckService;
	
	@RequiresPermissions("yaogan:deviceDynamicCheck:view")
	@GetMapping()
	public String deviceDynamicCheck()
	{
	    return prefix + "/deviceDynamicCheck";
	}
	
	/**
	 * 查询遥测设备动态检查过程数据列表
	 */
	@RequiresPermissions("yaogan:deviceDynamicCheck:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceDynamicCheck deviceDynamicCheck)
	{
		startPage();
        List<DeviceDynamicCheck> list = deviceDynamicCheckService.selectDeviceDynamicCheckList(deviceDynamicCheck);
		return getDataTable(list);
	}
	
	/**
	 * 新增遥测设备动态检查过程数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存遥测设备动态检查过程数据
	 */
	@RequiresPermissions("yaogan:deviceDynamicCheck:add")
	@Log(title = "遥测设备动态检查过程数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceDynamicCheck deviceDynamicCheck)
	{		
		return toAjax(deviceDynamicCheckService.insertDeviceDynamicCheck(deviceDynamicCheck));
	}

	/**
	 * 修改遥测设备动态检查过程数据
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		DeviceDynamicCheck deviceDynamicCheck = deviceDynamicCheckService.selectDeviceDynamicCheckById(iD);
		mmap.put("deviceDynamicCheck", deviceDynamicCheck);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存遥测设备动态检查过程数据
	 */
	@RequiresPermissions("yaogan:deviceDynamicCheck:edit")
	@Log(title = "遥测设备动态检查过程数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceDynamicCheck deviceDynamicCheck)
	{		
		return toAjax(deviceDynamicCheckService.updateDeviceDynamicCheck(deviceDynamicCheck));
	}
	
	/**
	 * 删除遥测设备动态检查过程数据
	 */
	@RequiresPermissions("yaogan:deviceDynamicCheck:remove")
	@Log(title = "遥测设备动态检查过程数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceDynamicCheckService.deleteDeviceDynamicCheckByIds(ids));
	}
	
}
