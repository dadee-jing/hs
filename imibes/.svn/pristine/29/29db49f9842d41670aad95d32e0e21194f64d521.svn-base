package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.StationCheckLog;
import com.ruoyi.yaogan.service.IStationCheckLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 遥测设备自检 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/stationCheckLog")
public class StationCheckLogController extends BaseController
{
    private String prefix = "yaogan/stationCheckLog";
	
	@Autowired
	private IStationCheckLogService stationCheckLogService;
	
	@RequiresPermissions("yaogan:stationCheckLog:view")
	@GetMapping()
	public String stationCheckLog()
	{
	    return prefix + "/stationCheckLog";
	}
	
	/**
	 * 查询遥测设备自检列表
	 */
	@RequiresPermissions("yaogan:stationCheckLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StationCheckLog stationCheckLog)
	{
		startPage();
        List<StationCheckLog> list = stationCheckLogService.selectStationCheckLogList(stationCheckLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增遥测设备自检
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存遥测设备自检
	 */
	@RequiresPermissions("yaogan:stationCheckLog:add")
	@Log(title = "遥测设备自检", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(StationCheckLog stationCheckLog)
	{		
		return toAjax(stationCheckLogService.insertStationCheckLog(stationCheckLog));
	}

	/**
	 * 修改遥测设备自检
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		StationCheckLog stationCheckLog = stationCheckLogService.selectStationCheckLogById(iD);
		mmap.put("stationCheckLog", stationCheckLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存遥测设备自检
	 */
	@RequiresPermissions("yaogan:stationCheckLog:edit")
	@Log(title = "遥测设备自检", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(StationCheckLog stationCheckLog)
	{		
		return toAjax(stationCheckLogService.updateStationCheckLog(stationCheckLog));
	}
	
	/**
	 * 删除遥测设备自检
	 */
	@RequiresPermissions("yaogan:stationCheckLog:remove")
	@Log(title = "遥测设备自检", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(stationCheckLogService.deleteStationCheckLogByIds(ids));
	}
	
}
