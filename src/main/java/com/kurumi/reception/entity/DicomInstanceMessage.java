package com.kurumi.reception.entity;

/**
 * dicom instance描述信息
 * @author h2oco2
 *
 */
public class DicomInstanceMessage {
	private String sessionId;
	//检查UID
	private String studyInstanceUID;
	//序列UID
	private String seriesInstanceUID;
	//实例UID
	private String sOPInstanceUID;
	//实例号
	private String instanceNumber;
	//字符集
	private String characterSet;
	//压缩类型
	private String transferSyntaxUID;
	//SOP类型
	private String sOPClassUID;
	//检查类型
	private String modality;
	//序列编号
	private String seriesNumber;
	
	//dicom文件存储的位置
	private String dcmFileStorageLocation;
	//处理状态
	private Integer status;
	//处理完结束状态 (成功,失败)
	private Integer processedStatus;
	//是否压缩 1.压缩, 2没有压缩
	private Integer isCompression;
	//压缩类型 1.高级jpg压缩, 2.jp2000压缩, 3.近无损压缩 0
	private Integer compressionType;
	
	public DicomInstanceMessage(String sessionId, String studyInstanceUID, String seriesInstanceUID, String sOPInstanceUID,
			String instanceNumber, String characterSet, String transferSyntaxUID, String sOPClassUID, String modality,
			String seriesNumber, String dcmFileStorageLocation, Integer status, Integer isCompression, 
			Integer compressionType) {
		super();
		this.sessionId = sessionId;
		this.studyInstanceUID = studyInstanceUID;
		this.seriesInstanceUID = seriesInstanceUID;
		this.sOPInstanceUID = sOPInstanceUID;
		this.instanceNumber = instanceNumber;
		this.characterSet = characterSet;
		this.transferSyntaxUID = transferSyntaxUID;
		this.sOPClassUID = sOPClassUID;
		this.modality = modality;
		this.seriesNumber = seriesNumber;
		this.dcmFileStorageLocation = dcmFileStorageLocation;
		this.status = status;
		this.processedStatus = processedStatus;
		this.isCompression = isCompression;
		this.compressionType = compressionType;
	}

	public DicomInstanceMessage() {
		super();
	}

	public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}

	public String getSeriesInstanceUID() {
		return seriesInstanceUID;
	}

	public void setSeriesInstanceUID(String seriesInstanceUID) {
		this.seriesInstanceUID = seriesInstanceUID;
	}

	public String getsOPInstanceUID() {
		return sOPInstanceUID;
	}

	public void setsOPInstanceUID(String sOPInstanceUID) {
		this.sOPInstanceUID = sOPInstanceUID;
	}

	public String getInstanceNumber() {
		return instanceNumber;
	}

	public void setInstanceNumber(String instanceNumber) {
		this.instanceNumber = instanceNumber;
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}

	public String getTransferSyntaxUID() {
		return transferSyntaxUID;
	}

	public void setTransferSyntaxUID(String transferSyntaxUID) {
		this.transferSyntaxUID = transferSyntaxUID;
	}

	public String getsOPClassUID() {
		return sOPClassUID;
	}

	public void setsOPClassUID(String sOPClassUID) {
		this.sOPClassUID = sOPClassUID;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	public String getDcmFileStorageLocation() {
		return dcmFileStorageLocation;
	}

	public void setDcmFileStorageLocation(String dcmFileStorageLocation) {
		this.dcmFileStorageLocation = dcmFileStorageLocation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProcessedStatus() {
		return processedStatus;
	}

	public void setProcessedStatus(Integer processedStatus) {
		this.processedStatus = processedStatus;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getIsCompression() {
		return isCompression;
	}

	public void setIsCompression(Integer isCompression) {
		this.isCompression = isCompression;
	}

	public Integer getCompressionType() {
		return compressionType;
	}

	public void setCompressionType(Integer compressionType) {
		this.compressionType = compressionType;
	}
	
	

	

}
