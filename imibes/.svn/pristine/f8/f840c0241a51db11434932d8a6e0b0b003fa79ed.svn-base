package com.ruoyi.yaogan.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.yaogan.domain.Line;
import com.ruoyi.yaogan.mapper.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 点位遥测线 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service
public class LineServiceImpl implements ILineService 
{
	@Autowired
	private LineMapper lineMapper;

	/**
     * 查询点位遥测线信息
     * 
     * @param iD 点位遥测线ID
     * @return 点位遥测线信息
     */
    @Override
	public Line selectLineById(Integer iD)
	{
	    return lineMapper.selectLineById(iD);
	}
	
	/**
     * 查询点位遥测线列表
     * 
     * @param line 点位遥测线信息
     * @return 点位遥测线集合
     */
	@Override
	public List<Line> selectLineList(Line line)
	{
	    return lineMapper.selectLineList(line);
	}
	
    /**
     * 新增点位遥测线
     * 
     * @param line 点位遥测线信息
     * @return 结果
     */
	@Override
	public int insertLine(Line line)
	{
	    return lineMapper.insertLine(line);
	}
	
	/**
     * 修改点位遥测线
     * 
     * @param line 点位遥测线信息
     * @return 结果
     */
	@Override
	public int updateLine(Line line)
	{
	    return lineMapper.updateLine(line);
	}

	/**
     * 删除点位遥测线对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLineByIds(String ids)
	{
		return lineMapper.deleteLineByIds(Convert.toStrArray(ids));
	}
	
}
