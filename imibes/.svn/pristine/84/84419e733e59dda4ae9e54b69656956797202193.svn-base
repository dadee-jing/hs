package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.DeviceCheckLog;
import com.ruoyi.yaogan.service.IDeviceCheckLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 遥测设备检查 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/deviceCheckLog")
public class DeviceCheckLogController extends BaseController
{
    private String prefix = "yaogan/deviceCheckLog";
	
	@Autowired
	private IDeviceCheckLogService deviceCheckLogService;
	
	@RequiresPermissions("yaogan:deviceCheckLog:view")
	@GetMapping()
	public String deviceCheckLog()
	{
	    return prefix + "/deviceCheckLog";
	}
	
	/**
	 * 查询遥测设备检查列表
	 */
	@RequiresPermissions("yaogan:deviceCheckLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceCheckLog deviceCheckLog)
	{
		startPage();
        List<DeviceCheckLog> list = deviceCheckLogService.selectDeviceCheckLogList(deviceCheckLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增遥测设备检查
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存遥测设备检查
	 */
	@RequiresPermissions("yaogan:deviceCheckLog:add")
	@Log(title = "遥测设备检查", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceCheckLog deviceCheckLog)
	{		
		return toAjax(deviceCheckLogService.insertDeviceCheckLog(deviceCheckLog));
	}

	/**
	 * 修改遥测设备检查
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		DeviceCheckLog deviceCheckLog = deviceCheckLogService.selectDeviceCheckLogById(iD);
		mmap.put("deviceCheckLog", deviceCheckLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存遥测设备检查
	 */
	@RequiresPermissions("yaogan:deviceCheckLog:edit")
	@Log(title = "遥测设备检查", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceCheckLog deviceCheckLog)
	{		
		return toAjax(deviceCheckLogService.updateDeviceCheckLog(deviceCheckLog));
	}
	
	/**
	 * 删除遥测设备检查
	 */
	@RequiresPermissions("yaogan:deviceCheckLog:remove")
	@Log(title = "遥测设备检查", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceCheckLogService.deleteDeviceCheckLogByIds(ids));
	}
	
}
