package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.AirQualityRecord;
import com.ruoyi.yaogan.service.IAirQualityRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点位环境空气质量记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/airQualityRecord")
public class AirQualityRecordController extends BaseController
{
    private String prefix = "yaogan/airQualityRecord";
	
	@Autowired
	private IAirQualityRecordService airQualityRecordService;
	
	@RequiresPermissions("yaogan:airQualityRecord:view")
	@GetMapping()
	public String airQualityRecord()
	{
	    return prefix + "/airQualityRecord";
	}
	
	/**
	 * 查询点位环境空气质量记录列表
	 */
	@RequiresPermissions("yaogan:airQualityRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AirQualityRecord airQualityRecord)
	{
		startPage();
        List<AirQualityRecord> list = airQualityRecordService.selectAirQualityRecordList(airQualityRecord);
		return getDataTable(list);
	}
	
	/**
	 * 新增点位环境空气质量记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存点位环境空气质量记录
	 */
	@RequiresPermissions("yaogan:airQualityRecord:add")
	@Log(title = "点位环境空气质量记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AirQualityRecord airQualityRecord)
	{		
		return toAjax(airQualityRecordService.insertAirQualityRecord(airQualityRecord));
	}

	/**
	 * 修改点位环境空气质量记录
	 */
	@GetMapping("/edit/{jLSJ}")
	public String edit(@PathVariable("jLSJ") String jLSJ, ModelMap mmap)
	{
		AirQualityRecord airQualityRecord = airQualityRecordService.selectAirQualityRecordById(jLSJ);
		mmap.put("airQualityRecord", airQualityRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存点位环境空气质量记录
	 */
	@RequiresPermissions("yaogan:airQualityRecord:edit")
	@Log(title = "点位环境空气质量记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AirQualityRecord airQualityRecord)
	{		
		return toAjax(airQualityRecordService.updateAirQualityRecord(airQualityRecord));
	}
	
	/**
	 * 删除点位环境空气质量记录
	 */
	@RequiresPermissions("yaogan:airQualityRecord:remove")
	@Log(title = "点位环境空气质量记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(airQualityRecordService.deleteAirQualityRecordByIds(ids));
	}
	
}
