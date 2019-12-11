package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.VehicleTrajectory;
import com.ruoyi.yaogan.service.IVehicleTrajectoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机动车轨迹 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/vehicleTrajectory")
public class VehicleTrajectoryController extends BaseController
{
    private String prefix = "yaogan/vehicleTrajectory";
	
	@Autowired
	private IVehicleTrajectoryService vehicleTrajectoryService;
	
	@RequiresPermissions("yaogan:vehicleTrajectory:view")
	@GetMapping()
	public String vehicleTrajectory()
	{
	    return prefix + "/vehicleTrajectory";
	}
	
	/**
	 * 查询机动车轨迹列表
	 */
	@RequiresPermissions("yaogan:vehicleTrajectory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VehicleTrajectory vehicleTrajectory)
	{
		startPage();
        List<VehicleTrajectory> list = vehicleTrajectoryService.selectVehicleTrajectoryList(vehicleTrajectory);
		return getDataTable(list);
	}
	
	/**
	 * 新增机动车轨迹
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机动车轨迹
	 */
	@RequiresPermissions("yaogan:vehicleTrajectory:add")
	@Log(title = "机动车轨迹", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VehicleTrajectory vehicleTrajectory)
	{		
		return toAjax(vehicleTrajectoryService.insertVehicleTrajectory(vehicleTrajectory));
	}

	/**
	 * 修改机动车轨迹
	 */
	@GetMapping("/edit/{gJXXBH}")
	public String edit(@PathVariable("gJXXBH") String gJXXBH, ModelMap mmap)
	{
		VehicleTrajectory vehicleTrajectory = vehicleTrajectoryService.selectVehicleTrajectoryById(gJXXBH);
		mmap.put("vehicleTrajectory", vehicleTrajectory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机动车轨迹
	 */
	@RequiresPermissions("yaogan:vehicleTrajectory:edit")
	@Log(title = "机动车轨迹", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VehicleTrajectory vehicleTrajectory)
	{		
		return toAjax(vehicleTrajectoryService.updateVehicleTrajectory(vehicleTrajectory));
	}
	
	/**
	 * 删除机动车轨迹
	 */
	@RequiresPermissions("yaogan:vehicleTrajectory:remove")
	@Log(title = "机动车轨迹", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vehicleTrajectoryService.deleteVehicleTrajectoryByIds(ids));
	}
	
}
