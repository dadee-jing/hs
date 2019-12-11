package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.TrafficFlow;
import com.ruoyi.yaogan.service.ITrafficFlowService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 交通流量 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/trafficFlow")
public class TrafficFlowController extends BaseController
{
    private String prefix = "yaogan/trafficFlow";
	
	@Autowired
	private ITrafficFlowService trafficFlowService;
	
	@RequiresPermissions("yaogan:trafficFlow:view")
	@GetMapping()
	public String trafficFlow()
	{
	    return prefix + "/trafficFlow";
	}
	
	/**
	 * 查询交通流量列表
	 */
	@RequiresPermissions("yaogan:trafficFlow:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrafficFlow trafficFlow)
	{
		startPage();
        List<TrafficFlow> list = trafficFlowService.selectTrafficFlowList(trafficFlow);
		return getDataTable(list);
	}
	
	/**
	 * 新增交通流量
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存交通流量
	 */
	@RequiresPermissions("yaogan:trafficFlow:add")
	@Log(title = "交通流量", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrafficFlow trafficFlow)
	{		
		return toAjax(trafficFlowService.insertTrafficFlow(trafficFlow));
	}

	/**
	 * 修改交通流量
	 */
	@GetMapping("/edit/{dWBH}")
	public String edit(@PathVariable("dWBH") String dWBH, ModelMap mmap)
	{
		TrafficFlow trafficFlow = trafficFlowService.selectTrafficFlowById(dWBH);
		mmap.put("trafficFlow", trafficFlow);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存交通流量
	 */
	@RequiresPermissions("yaogan:trafficFlow:edit")
	@Log(title = "交通流量", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrafficFlow trafficFlow)
	{		
		return toAjax(trafficFlowService.updateTrafficFlow(trafficFlow));
	}
	
	/**
	 * 删除交通流量
	 */
	@RequiresPermissions("yaogan:trafficFlow:remove")
	@Log(title = "交通流量", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trafficFlowService.deleteTrafficFlowByIds(ids));
	}
	
}
