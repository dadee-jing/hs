package com.ruoyi.duge.service;


import com.ruoyi.duge.domain.AuditRecord;

import java.util.List;

/**
 * 站点审查记录 服务层
 * 
 * @author ruoyi
 * @date 2019-04-16
 */
public interface IAuditRecordService 
{
	/**
     * 查询站点审查记录信息
     * 
     * @param id 站点审查记录ID
     * @return 站点审查记录信息
     */
	public AuditRecord selectAuditRecordById(Integer id);
	
	/**
     * 查询站点审查记录列表
     * 
     * @param auditRecord 站点审查记录信息
     * @return 站点审查记录集合
     */
	public List<AuditRecord> selectAuditRecordList(AuditRecord auditRecord);
	
	/**
     * 新增站点审查记录
     * 
     * @param auditRecord 站点审查记录信息
     * @return 结果
     */
	public int insertAuditRecord(AuditRecord auditRecord);
	
	/**
     * 修改站点审查记录
     * 
     * @param auditRecord 站点审查记录信息
     * @return 结果
     */
	public int updateAuditRecord(AuditRecord auditRecord);
		
	/**
     * 删除站点审查记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuditRecordByIds(String ids);
	
}
