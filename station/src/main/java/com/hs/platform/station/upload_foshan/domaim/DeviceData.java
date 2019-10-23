package com.hs.platform.station.upload_foshan.domaim;

import java.util.Date;

/**
 * 重量数据表 weight_data
 * 
 * @author ruoyi
 * @date 2018-11-20
 */
public class DeviceData extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 设备编号 */
	private String indexCode;
	/** 检测时间 */
	private Date checkDate;
	/** 设备类型，0称重、1抓拍、2大屏 */
	private String deviceType;
	/** 序号 */
	private Integer checkId;
	/** 设备IP */
	private String ipAddress;
	/** 设备状态 */
	private Integer checkState;
	/** 异常描述 */
	private String exceptionDesc;
	/** 上传标记 */
	private Integer uploadTag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public Integer getUploadTag() {
		return uploadTag;
	}

	public void setUploadTag(Integer uploadTag) {
		this.uploadTag = uploadTag;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	@Override
	public String toString() {
		return "DeviceData{" +
				"id=" + id +
				", indexCode=" + indexCode +
				", checkDate=" + checkDate +
				", deviceType='" + deviceType + '\'' +
				", checkId=" + checkId +
				", ipAddress='" + ipAddress + '\'' +
				", checkState=" + checkState +
				", exceptionDesc=" + exceptionDesc +
				", uploadTag=" + uploadTag +
				'}';
	}
}
