package com.ruoyi.web.controller.duge;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.duge.domain.AuditRecord;
import com.ruoyi.duge.service.IAuditRecordService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 站点审查记录 信息操作处理
 *
 * @author ruoyi
 * @date 2019-04-16
 */
@Controller
@RequestMapping("/station/auditRecord")
public class AuditRecordController extends BaseController {
    private String prefix = "auditRecord";

    @Autowired
    private IAuditRecordService auditRecordService;

    @GetMapping()
    public String auditRecord() {
        return prefix + "/auditRecord";
    }

    /**
     * 查询站点审查记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AuditRecord auditRecord) {
        startPage();
        List<AuditRecord> list = auditRecordService.selectAuditRecordList(auditRecord);
        return getDataTable(list);
    }

    /**
     * 新增站点审查记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存站点审查记录
     */
    @Log(title = "站点审查记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AuditRecord auditRecord) {
        return toAjax(auditRecordService.insertAuditRecord(auditRecord));
    }

    /**
     * 修改站点审查记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        AuditRecord auditRecord = auditRecordService.selectAuditRecordById(id);
        mmap.put("auditRecord", auditRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存站点审查记录
     */
    @Log(title = "站点审查记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AuditRecord auditRecord) {
        return toAjax(auditRecordService.updateAuditRecord(auditRecord));
    }

    /**
     * 删除站点审查记录
     */
    @Log(title = "站点审查记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(auditRecordService.deleteAuditRecordByIds(ids));
    }

}
