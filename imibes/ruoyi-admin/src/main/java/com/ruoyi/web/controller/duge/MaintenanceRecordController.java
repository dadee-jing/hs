package com.ruoyi.web.controller.duge;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.duge.domain.MaintenanceRecord;
import com.ruoyi.duge.service.IMaintenanceRecordService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 站点维护记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
@Controller
@RequestMapping("/station/maintenanceRecord")
public class MaintenanceRecordController extends BaseController
{
    private String prefix = "maintenanceRecord";
	
	@Autowired
	private IMaintenanceRecordService maintenanceRecordService;

	@GetMapping()
	public String maintenanceRecord()
	{
	    return prefix + "/maintenanceRecord";
	}
	
	/**
	 * 查询站点维护记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MaintenanceRecord maintenanceRecord)
	{
		startPage();
        List<MaintenanceRecord> list = maintenanceRecordService.selectMaintenanceRecordList(maintenanceRecord);
		return getDataTable(list);
	}
	
	/**
	 * 新增站点维护记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存站点维护记录
	 */
	@Log(title = "站点维护记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MaintenanceRecord maintenanceRecord)
	{		
		return toAjax(maintenanceRecordService.insertMaintenanceRecord(maintenanceRecord));
	}

	/**
	 * 修改站点维护记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MaintenanceRecord maintenanceRecord = maintenanceRecordService.selectMaintenanceRecordById(id);
		mmap.put("maintenanceRecord", maintenanceRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存站点维护记录
	 */
	@Log(title = "站点维护记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MaintenanceRecord maintenanceRecord)
	{		
		return toAjax(maintenanceRecordService.updateMaintenanceRecord(maintenanceRecord));
	}
	
	/**
	 * 删除站点维护记录
	 */
	@Log(title = "站点维护记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(maintenanceRecordService.deleteMaintenanceRecordByIds(ids));
	}
	
}
