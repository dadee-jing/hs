package com.ruoyi.web.controller.duge;

import com.ruoyi.duge.domain.DeptTest;
import com.ruoyi.duge.domain.StationType;
import com.ruoyi.duge.mapper.StationTypeMapper;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Comparator.comparing;

/**
 * 站点 信息操作处理
 *
 * @author ruoyi
 * @date 2018-11-22
 */
@Controller
@RequestMapping("/module/stationType")
public class StationTypeController extends BaseController {
    private String prefix = "stationType";

    @Autowired
    private StationTypeMapper stationTypeMapper;


    @GetMapping()
    public String stationType()
    {
        return prefix + "/stationType";
    }

    @GetMapping("/list")
    @ResponseBody
    public  List<StationType> list() {
        List<StationType> stationTypeList = stationTypeMapper.selectStationTypeList();
        return stationTypeList;
    }

    @GetMapping("/list2")
    @ResponseBody
    public  List<DeptTest> list2() {
        List<DeptTest> stationTypeList = stationTypeMapper.selectDeptTest();
        return stationTypeList;
    }

/*
    */
/**
     * 选择部门树
     *//*

    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    */
/**
     * 加载部门列表树
     *//*

    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = deptService.selectDeptTree();
        return tree;
    }

*/

}
