package com.ruoyi.duge.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.duge.domain.AuditRecord;
import com.ruoyi.duge.mapper.AuditRecordMapper;
import com.ruoyi.duge.service.IAuditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点审查记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
@Service
public class AuditRecordServiceImpl implements IAuditRecordService
{
	@Autowired
	private AuditRecordMapper auditRecordMapper;

	/**
     * 查询站点审查记录信息
     * 
     * @param id 站点审查记录ID
     * @return 站点审查记录信息
     */
    @Override
	public AuditRecord selectAuditRecordById(Integer id)
	{
	    return auditRecordMapper.selectAuditRecordById(id);
	}
	
	/**
     * 查询站点审查记录列表
     * 
     * @param auditRecord 站点审查记录信息
     * @return 站点审查记录集合
     */
	@Override
	public List<AuditRecord> selectAuditRecordList(AuditRecord auditRecord)
	{
	    return auditRecordMapper.selectAuditRecordList(auditRecord);
	}
	
    /**
     * 新增站点审查记录
     * 
     * @param auditRecord 站点审查记录信息
     * @return 结果
     */
	@Override
	public int insertAuditRecord(AuditRecord auditRecord)
	{
	    return auditRecordMapper.insertAuditRecord(auditRecord);
	}
	
	/**
     * 修改站点审查记录
     * 
     * @param auditRecord 站点审查记录信息
     * @return 结果
     */
	@Override
	public int updateAuditRecord(AuditRecord auditRecord)
	{
	    return auditRecordMapper.updateAuditRecord(auditRecord);
	}

	/**
     * 删除站点审查记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAuditRecordByIds(String ids)
	{
		return auditRecordMapper.deleteAuditRecordByIds(Convert.toStrArray(ids));
	}
	
}
