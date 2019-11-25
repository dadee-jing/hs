package com.ruoyi.web.controller.duge;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duge.domain.StationInfo;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.mapper.WeightDataMapper;
import com.ruoyi.duge.service.IStationInfoService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.DateUtils.YYYY_MM_DD_HH_MM_SS;
import static java.util.Comparator.comparing;

/**
 * 站点 信息操作处理
 *
 * @author ruoyi
 * @date 2018-11-22
 */
@Controller
@RequestMapping("/module/stationInfo")
public class StationInfoController extends BaseController {
    private String prefix = "stationInfo";

    @Autowired
    private IStationInfoService stationInfoService;

    @Autowired
    private WeightDataMapper weightDataMapper;


    /**
     * 站点增删改管理
     *
     * @return
     */
    @RequiresPermissions("module:stationInfo:manager")
    @GetMapping()
    public String stationInfo() {
        return prefix + "/stationInfo";
    }

    /**
     * 站点详情
     *
     * @return
     */
    @RequiresPermissions("module:stationInfo:view")
    @GetMapping("/stationDashboard")
    public String stationDashboard() {
        return prefix + "/stationDashboard";
    }

    /**
     * 查询站点列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StationInfo stationInfo) {
        startPage();
        List<StationInfo> list = stationInfoService.selectStationInfoList(stationInfo);
        return getDataTable(list);
    }

    @PostMapping("/listWithData")
    @ResponseBody
    public TableDataInfo listWithData(StationInfo stationInfo) {
        startPage();
        List<StationInfo> list = stationInfoService.selectStationInfoList(stationInfo);
        WeightData weightData = new WeightData();
        weightData.getParams().put("timeStr", DateUtils.parseDateToStr(YYYY_MM_DD_HH_MM_SS,
                new Date(new Date().getTime() - 1000 * 60 * 60)));
        List<Map<String, Object>> hourList = weightDataMapper.selectCountHourList(weightData);
        Map<Integer, Long> stationCountMap = hourList.stream().collect(Collectors.toMap(
                entityMap -> entityMap.get("station_id") != null ? ((Long) entityMap.get("station_id")).intValue() : 0,
                entityMap -> entityMap.get("over_count") != null ? ((Long) entityMap.get("over_count")) : 0,
                (k1, k2) -> k1));
        list.forEach(stationInfo1 -> {
            stationInfo1.setHourOverCount(stationCountMap.get(stationInfo1.getId()) != null ? stationCountMap.get(stationInfo1.getId()) : 0L);
        });
        list = list.stream().sorted(comparing(StationInfo::getHourOverCount).reversed()).collect(Collectors.toList());
        for (int i = 1; i <= list.size(); i++) {
            list.get(i - 1).setIndex(i);
        }
        return getDataTable(list);
    }

    @GetMapping("/list")
    public String getList() {
        return prefix + "/selectList";
    }

    /**
     * 新增站点
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存站点
     */
    @RequiresPermissions("module:stationInfo:add")
    @Log(title = "站点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StationInfo stationInfo) {
        return toAjax(stationInfoService.insertStationInfo(stationInfo));
    }

    /**
     * 修改站点
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        StationInfo stationInfo = stationInfoService.selectStationInfoById(id);
        mmap.put("stationInfo", stationInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存站点
     */
    @RequiresPermissions("module:stationInfo:edit")
    @Log(title = "站点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StationInfo stationInfo) {
        return toAjax(stationInfoService.updateStationInfo(stationInfo));
    }

    /**
     * 删除站点
     */
    @RequiresPermissions("module:stationInfo:remove")
    @Log(title = "站点", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(stationInfoService.deleteStationInfoByIds(ids));
    }

}
