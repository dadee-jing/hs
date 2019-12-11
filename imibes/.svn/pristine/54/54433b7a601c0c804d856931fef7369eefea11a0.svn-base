package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.MobileStation;
import com.ruoyi.yaogan.service.IMobileStationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移动式点位运行记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/mobileStation")
public class MobileStationController extends BaseController
{
    private String prefix = "yaogan/mobileStation";
	
	@Autowired
	private IMobileStationService mobileStationService;
	
	@RequiresPermissions("yaogan:mobileStation:view")
	@GetMapping()
	public String mobileStation()
	{
	    return prefix + "/mobileStation";
	}
	
	/**
	 * 查询移动式点位运行记录列表
	 */
	@RequiresPermissions("yaogan:mobileStation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MobileStation mobileStation)
	{
		startPage();
        List<MobileStation> list = mobileStationService.selectMobileStationList(mobileStation);
		return getDataTable(list);
	}
	
	/**
	 * 新增移动式点位运行记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存移动式点位运行记录
	 */
	@RequiresPermissions("yaogan:mobileStation:add")
	@Log(title = "移动式点位运行记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MobileStation mobileStation)
	{		
		return toAjax(mobileStationService.insertMobileStation(mobileStation));
	}

	/**
	 * 修改移动式点位运行记录
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		MobileStation mobileStation = mobileStationService.selectMobileStationById(iD);
		mmap.put("mobileStation", mobileStation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存移动式点位运行记录
	 */
	@RequiresPermissions("yaogan:mobileStation:edit")
	@Log(title = "移动式点位运行记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MobileStation mobileStation)
	{		
		return toAjax(mobileStationService.updateMobileStation(mobileStation));
	}
	
	/**
	 * 删除移动式点位运行记录
	 */
	@RequiresPermissions("yaogan:mobileStation:remove")
	@Log(title = "移动式点位运行记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mobileStationService.deleteMobileStationByIds(ids));
	}
	
}
