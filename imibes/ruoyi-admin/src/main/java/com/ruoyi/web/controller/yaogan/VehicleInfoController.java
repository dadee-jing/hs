package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.VehicleInfo;
import com.ruoyi.yaogan.service.IVehicleInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/vehicleInfo")
public class VehicleInfoController extends BaseController
{
    private String prefix = "yaogan/vehicleInfo";
	
	@Autowired
	private IVehicleInfoService vehicleInfoService;
	
	@RequiresPermissions("yaogan:vehicleInfo:view")
	@GetMapping()
	public String vehicleInfo()
	{
	    return prefix + "/vehicleInfo";
	}
	
	/**
	 * 查询车辆数据列表
	 */
	@RequiresPermissions("yaogan:vehicleInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VehicleInfo vehicleInfo)
	{
		startPage();
        List<VehicleInfo> list = vehicleInfoService.selectVehicleInfoList(vehicleInfo);
		return getDataTable(list);
	}
	
	/**
	 * 新增车辆数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车辆数据
	 */
	@RequiresPermissions("yaogan:vehicleInfo:add")
	@Log(title = "车辆数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VehicleInfo vehicleInfo)
	{		
		return toAjax(vehicleInfoService.insertVehicleInfo(vehicleInfo));
	}

	/**
	 * 修改车辆数据
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		VehicleInfo vehicleInfo = vehicleInfoService.selectVehicleInfoById(iD);
		mmap.put("vehicleInfo", vehicleInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存车辆数据
	 */
	@RequiresPermissions("yaogan:vehicleInfo:edit")
	@Log(title = "车辆数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VehicleInfo vehicleInfo)
	{		
		return toAjax(vehicleInfoService.updateVehicleInfo(vehicleInfo));
	}
	
	/**
	 * 删除车辆数据
	 */
	@RequiresPermissions("yaogan:vehicleInfo:remove")
	@Log(title = "车辆数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vehicleInfoService.deleteVehicleInfoByIds(ids));
	}
	
}
