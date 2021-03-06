package com.ruoyi.web.controller.duge;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.duge.domain.MaintenanceRecord;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.mapper.StationInfoMapper;
import com.ruoyi.duge.service.IMaintenanceRecordService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

	@Autowired
	private StationInfoMapper stationInfoMapper;

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
	public TableDataInfo list(MaintenanceRecord maintenanceRecord) {
		startPage();
        List<MaintenanceRecord> list = maintenanceRecordService.selectMaintenanceRecordList(maintenanceRecord);
		return getDataTable(list);
	}

	@PostMapping("/stationRecordList/{stationId}")
	@ResponseBody
	public TableDataInfo stationRecordList(@PathVariable("stationId") Integer stationId, ModelMap mmap) {
		startPage();
        List<MaintenanceRecord> maintenanceRecordList = maintenanceRecordService.selectMaintenanceRecordListByStationId(stationId);
		mmap.put("maintenanceRecordList",maintenanceRecordList);
		return getDataTable(maintenanceRecordList);
	}
	
	/**
	 * 新增站点维护记录
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		List<StationInfo> stationInfoList = stationInfoMapper.selectStationInfoList(null);
		SysUser user = getUser();
		//当天登记的记录数
		int todayCount = maintenanceRecordService.getTodayRecordCount() + 1;
		String count = todayCount + "";
		if(todayCount < 10){
			count = "0" + todayCount;
		}
		mmap.put("stationList",stationInfoList);
		mmap.put("user",user);
		mmap.put("count",count);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存站点维护记录
	 */
	@Log(title = "站点维护记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MaintenanceRecord maintenanceRecord) {
		maintenanceRecord.setCreateTime(new Date());
		maintenanceRecord.setCreateBy(getUser().getUserName());
		maintenanceRecord.setUpdateTime(new Date());
		maintenanceRecord.setUpdateBy(getUser().getUserName());
		return toAjax(maintenanceRecordService.insertMaintenanceRecord(maintenanceRecord));
	}

	/**
	 * 修改站点维护记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		MaintenanceRecord maintenanceRecord = maintenanceRecordService.selectMaintenanceRecordById(id);
		List<StationInfo> stationInfoList = stationInfoMapper.selectStationInfoList(null);
		SysUser user = getUser();
		int stationId = maintenanceRecordService.getStationIdByRecordId(id);
		StationInfo stationInfo = stationInfoMapper.selectStationInfoByIdNoLane(stationId);
		mmap.put("maintenanceRecord", maintenanceRecord);
		mmap.put("stationList",stationInfoList);
		mmap.put("stationInfo",stationInfo);
		mmap.put("user",user);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存站点维护记录
	 */
	@Log(title = "站点维护记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MaintenanceRecord maintenanceRecord) {
		maintenanceRecord.setUpdateTime(new Date());
		maintenanceRecord.setUpdateBy(getUser().getUserName());
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


	/**
	 * 修改站点维护记录
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, ModelMap mmap) {
		MaintenanceRecord maintenanceRecord = maintenanceRecordService.selectMaintenanceRecordById(id);
		Integer stationId = maintenanceRecordService.getStationIdByRecordId(id);
		StationInfo stationInfo = stationInfoMapper.selectStationInfoByIdNoLane(stationId);
		mmap.put("record", maintenanceRecord);
		mmap.put("station",stationInfo);
		return prefix + "/detail";
	}

}
