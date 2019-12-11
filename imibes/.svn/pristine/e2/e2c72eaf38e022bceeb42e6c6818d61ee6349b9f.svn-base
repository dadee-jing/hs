package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.DeviceSelfCheck;
import com.ruoyi.yaogan.service.IDeviceSelfCheckService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 遥测设备自检过程数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/deviceSelfCheck")
public class DeviceSelfCheckController extends BaseController
{
    private String prefix = "yaogan/deviceSelfCheck";
	
	@Autowired
	private IDeviceSelfCheckService deviceSelfCheckService;
	
	@RequiresPermissions("yaogan:deviceSelfCheck:view")
	@GetMapping()
	public String deviceSelfCheck()
	{
	    return prefix + "/deviceSelfCheck";
	}
	
	/**
	 * 查询遥测设备自检过程数据列表
	 */
	@RequiresPermissions("yaogan:deviceSelfCheck:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceSelfCheck deviceSelfCheck)
	{
		startPage();
        List<DeviceSelfCheck> list = deviceSelfCheckService.selectDeviceSelfCheckList(deviceSelfCheck);
		return getDataTable(list);
	}
	
	/**
	 * 新增遥测设备自检过程数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存遥测设备自检过程数据
	 */
	@RequiresPermissions("yaogan:deviceSelfCheck:add")
	@Log(title = "遥测设备自检过程数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceSelfCheck deviceSelfCheck)
	{		
		return toAjax(deviceSelfCheckService.insertDeviceSelfCheck(deviceSelfCheck));
	}

	/**
	 * 修改遥测设备自检过程数据
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		DeviceSelfCheck deviceSelfCheck = deviceSelfCheckService.selectDeviceSelfCheckById(iD);
		mmap.put("deviceSelfCheck", deviceSelfCheck);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存遥测设备自检过程数据
	 */
	@RequiresPermissions("yaogan:deviceSelfCheck:edit")
	@Log(title = "遥测设备自检过程数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceSelfCheck deviceSelfCheck)
	{		
		return toAjax(deviceSelfCheckService.updateDeviceSelfCheck(deviceSelfCheck));
	}
	
	/**
	 * 删除遥测设备自检过程数据
	 */
	@RequiresPermissions("yaogan:deviceSelfCheck:remove")
	@Log(title = "遥测设备自检过程数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceSelfCheckService.deleteDeviceSelfCheckByIds(ids));
	}
	
}
