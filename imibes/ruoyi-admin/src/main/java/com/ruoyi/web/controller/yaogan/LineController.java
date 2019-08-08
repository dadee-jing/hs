package com.ruoyi.web.controller.yaogan;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.yaogan.domain.Line;
import com.ruoyi.yaogan.service.ILineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点位遥测线 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Controller
@RequestMapping("/yaogan/line")
public class LineController extends BaseController
{
    private String prefix = "yaogan/line";
	
	@Autowired
	private ILineService lineService;
	
	@RequiresPermissions("yaogan:line:view")
	@GetMapping()
	public String line()
	{
	    return prefix + "/line";
	}
	
	/**
	 * 查询点位遥测线列表
	 */
	@RequiresPermissions("yaogan:line:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Line line)
	{
		startPage();
        List<Line> list = lineService.selectLineList(line);
		return getDataTable(list);
	}
	
	/**
	 * 新增点位遥测线
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存点位遥测线
	 */
	@RequiresPermissions("yaogan:line:add")
	@Log(title = "点位遥测线", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Line line)
	{		
		return toAjax(lineService.insertLine(line));
	}

	/**
	 * 修改点位遥测线
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Integer iD, ModelMap mmap)
	{
		Line line = lineService.selectLineById(iD);
		mmap.put("line", line);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存点位遥测线
	 */
	@RequiresPermissions("yaogan:line:edit")
	@Log(title = "点位遥测线", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Line line)
	{		
		return toAjax(lineService.updateLine(line));
	}
	
	/**
	 * 删除点位遥测线
	 */
	@RequiresPermissions("yaogan:line:remove")
	@Log(title = "点位遥测线", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lineService.deleteLineByIds(ids));
	}
	
}
