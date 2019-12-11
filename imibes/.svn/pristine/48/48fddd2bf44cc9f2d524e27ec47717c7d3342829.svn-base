package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.DeviceStaticCheck;
import com.ruoyi.yaogan.service.IDeviceStaticCheckService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 遥测设备静态检查过程数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/deviceStaticCheck")
public class DeviceStaticCheckController extends BaseController
{
    private String prefix = "yaogan/deviceStaticCheck";
	
	@Autowired
	private IDeviceStaticCheckService deviceStaticCheckService;
	
	@RequiresPermissions("yaogan:deviceStaticCheck:view")
	@GetMapping()
	public String deviceStaticCheck()
	{
	    return prefix + "/deviceStaticCheck";
	}
	
	/**
	 * 查询遥测设备静态检查过程数据列表
	 */
	@RequiresPermissions("yaogan:deviceStaticCheck:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceStaticCheck deviceStaticCheck)
	{
		startPage();
        List<DeviceStaticCheck> list = deviceStaticCheckService.selectDeviceStaticCheckList(deviceStaticCheck);
		return getDataTable(list);
	}
	
	/**
	 * 新增遥测设备静态检查过程数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存遥测设备静态检查过程数据
	 */
	@RequiresPermissions("yaogan:deviceStaticCheck:add")
	@Log(title = "遥测设备静态检查过程数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceStaticCheck deviceStaticCheck)
	{		
		return toAjax(deviceStaticCheckService.insertDeviceStaticCheck(deviceStaticCheck));
	}

	/**
	 * 修改遥测设备静态检查过程数据
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		DeviceStaticCheck deviceStaticCheck = deviceStaticCheckService.selectDeviceStaticCheckById(iD);
		mmap.put("deviceStaticCheck", deviceStaticCheck);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存遥测设备静态检查过程数据
	 */
	@RequiresPermissions("yaogan:deviceStaticCheck:edit")
	@Log(title = "遥测设备静态检查过程数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceStaticCheck deviceStaticCheck)
	{		
		return toAjax(deviceStaticCheckService.updateDeviceStaticCheck(deviceStaticCheck));
	}
	
	/**
	 * 删除遥测设备静态检查过程数据
	 */
	@RequiresPermissions("yaogan:deviceStaticCheck:remove")
	@Log(title = "遥测设备静态检查过程数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceStaticCheckService.deleteDeviceStaticCheckByIds(ids));
	}
	
}
