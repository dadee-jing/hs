package com.ruoyi.web.controller.duge;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.duge.domain.StationType;
import com.ruoyi.duge.mapper.StationTypeMapper;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("stationType", stationTypeMapper.selectStationTypeById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StationType stationType) {
        stationType.setCreateBy(ShiroUtils.getLoginName());
        StationType info = stationTypeMapper.selectStationTypeById(stationType.getParentId());
        stationType.setAncestors(info.getAncestors() + "," + stationType.getParentId());
        return toAjax(stationTypeMapper.insertStationType(stationType));
    }


    /**
     * 修改
     */
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable("typeId") Long typeId, ModelMap mmap) {
        StationType stationType = stationTypeMapper.selectStationTypeById(typeId);
        if (StringUtils.isNotNull(stationType) && 100L == typeId) {
            stationType.setParentName("无");
        }
        mmap.put("stationType", stationType);
        return prefix + "/edit";
    }

    /**
     * 保存修改
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StationType stationType) {
        stationType.setUpdateBy(ShiroUtils.getLoginName());
        StationType info = stationTypeMapper.selectStationTypeById(stationType.getParentId());
/*        if (StringUtils.isNotNull(info)) {
            String ancestors = info.getAncestors() + "," + stationType.getParentId();
            stationType.setAncestors(ancestors);
            updateStationChildren(stationType.getTypeId(), ancestors);
        }*/
        return toAjax(stationTypeMapper.updateStationType(stationType));
    }

/*    *//**
     * 修改子元素关系
     *
     * @param deptId 部门ID
     * @param ancestors 元素列表
     *//*
    public void updateStationChildren(Long deptId, String ancestors) {
        StationType dept = new StationType();
        dept.setParentId(deptId);
        List<SysDept> childrens = stationTypeMapper.selectStationTypeList(dept);
        for (SysDept children : childrens) {
            children.setAncestors(ancestors + "," + dept.getParentId());
        }
        if (childrens.size() > 0) {
            deptMapper.updateDeptChildren(childrens);
        }
    }*/


    @PostMapping("/remove/{typeId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("typeId") Long typeId) {
        if (stationTypeMapper.selectTypeCount(typeId) > 0) {
            return error(1, "存在下级,不允许删除");
        }
        return toAjax(stationTypeMapper.deleteStationTypeById(typeId));
    }

    /**
     * 校验名称
     */
    @PostMapping("/checkTypeNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(StationType stationType) {
        Long typeId = StringUtils.isNull(stationType.getTypeId()) ? -1L : stationType.getTypeId();
        StationType info = stationTypeMapper.checkTypeNameUnique(stationType.getTypeName(), stationType.getParentId());
        if (StringUtils.isNotNull(info) && info.getTypeId().longValue() != typeId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    /**
     * 选择树
     */
    @GetMapping("/selectStationTypeTree/{typeId}")
    public String selectTypeTree(@PathVariable("typeId") Long typeId, ModelMap mmap) {
        mmap.put("stationType", stationTypeMapper.selectStationTypeById(typeId));
        return prefix + "/tree";
    }

    /**
     * 加载列表树
     */
    @GetMapping("/stationTypeTreeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<StationType> stationTypeList = stationTypeMapper.selectStationTypeList();
        trees = getTrees(stationTypeList, false, null);
        return trees;
    }


    /**
     * 加载列表树,无根节点
     */
    @GetMapping("/stationTypeTreeDataNoRoot")
    @ResponseBody
    public List<Map<String, Object>> treeDataNoRoot() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<StationType> stationTypeList = stationTypeMapper.selectStationTypeListNoRoot();
        trees = getTrees(stationTypeList, false, null);
        return trees;
    }


    public List<Map<String, Object>> getTrees(List<StationType> deptList, boolean isCheck, List<String> roleDeptList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (StationType dept : deptList)
        {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("id", dept.getTypeId());
                deptMap.put("pId", dept.getParentId());
                deptMap.put("name", dept.getTypeName());
                deptMap.put("title", dept.getTypeName());
                if (isCheck)
                {
                    deptMap.put("checked", roleDeptList.contains(dept.getTypeId() + dept.getTypeName()));
                }
                else
                {
                    deptMap.put("checked", false);
                }
                trees.add(deptMap);
            }
        return trees;
    }
}
