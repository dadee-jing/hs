package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.MonitorDataLog;
import com.ruoyi.yaogan.service.IMonitorDataLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 遥感监测数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/monitorDataLog")
public class MonitorDataLogController extends BaseController
{
    private String prefix = "yaogan/monitorDataLog";
	
	@Autowired
	private IMonitorDataLogService monitorDataLogService;
	
	@RequiresPermissions("yaogan:monitorDataLog:view")
	@GetMapping()
	public String monitorDataLog()
	{
	    return prefix + "/monitorDataLog";
	}
	
	/**
	 * 查询遥感监测数据列表
	 */
	@RequiresPermissions("yaogan:monitorDataLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MonitorDataLog monitorDataLog)
	{
		startPage();
        List<MonitorDataLog> list = monitorDataLogService.selectMonitorDataLogList(monitorDataLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增遥感监测数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存遥感监测数据
	 */
	@RequiresPermissions("yaogan:monitorDataLog:add")
	@Log(title = "遥感监测数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MonitorDataLog monitorDataLog)
	{		
		return toAjax(monitorDataLogService.insertMonitorDataLog(monitorDataLog));
	}

	/**
	 * 修改遥感监测数据
	 */
	@GetMapping("/edit/{jLBH}")
	public String edit(@PathVariable("jLBH") String jLBH, ModelMap mmap)
	{
		MonitorDataLog monitorDataLog = monitorDataLogService.selectMonitorDataLogById(jLBH);
		mmap.put("monitorDataLog", monitorDataLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存遥感监测数据
	 */
	@RequiresPermissions("yaogan:monitorDataLog:edit")
	@Log(title = "遥感监测数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MonitorDataLog monitorDataLog)
	{		
		return toAjax(monitorDataLogService.updateMonitorDataLog(monitorDataLog));
	}
	
	/**
	 * 删除遥感监测数据
	 */
	@RequiresPermissions("yaogan:monitorDataLog:remove")
	@Log(title = "遥感监测数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(monitorDataLogService.deleteMonitorDataLogByIds(ids));
	}
	
}
