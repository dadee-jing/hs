package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.Station;
import com.ruoyi.yaogan.service.IStationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点位 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/station")
public class StationController extends BaseController
{
    private String prefix = "yaogan/station";
	
	@Autowired
	private IStationService stationService;
	
	@RequiresPermissions("yaogan:station:view")
	@GetMapping()
	public String station()
	{
	    return prefix + "/station";
	}
	
	/**
	 * 查询点位列表
	 */
	@RequiresPermissions("yaogan:station:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Station station)
	{
		startPage();
        List<Station> list = stationService.selectStationList(station);
		return getDataTable(list);
	}
	
	/**
	 * 新增点位
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存点位
	 */
	@RequiresPermissions("yaogan:station:add")
	@Log(title = "点位", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Station station)
	{		
		return toAjax(stationService.insertStation(station));
	}

	/**
	 * 修改点位
	 */
	@GetMapping("/edit/{dWBH}")
	public String edit(@PathVariable("dWBH") String dWBH, ModelMap mmap)
	{
		Station station = stationService.selectStationById(dWBH);
		mmap.put("station", station);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存点位
	 */
	@RequiresPermissions("yaogan:station:edit")
	@Log(title = "点位", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Station station)
	{		
		return toAjax(stationService.updateStation(station));
	}
	
	/**
	 * 删除点位
	 */
	@RequiresPermissions("yaogan:station:remove")
	@Log(title = "点位", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(stationService.deleteStationByIds(ids));
	}
	
}
