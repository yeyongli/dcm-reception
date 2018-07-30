package com.kurumi.reception.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Study {
	//检查UID
	private String studyInstanceUID;
	private String studyID;
	private String studyDate;
	private String studyTime;
	private String accessionNumber;
	//科室名称
	private String departmentName;
	//科室编号
	private String departmentNumber;
	private Integer status;
	private Integer processedStatus;
	private BlockingDeque<DicomInstanceMessage> instanceQueue = new LinkedBlockingDeque<>();
	
	
	public String getStudyInstanceUID() {
		return studyInstanceUID;
	}
	public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}
	public String getStudyID() {
		return studyID;
	}
	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	public String getStudyDate() {
		return studyDate;
	}
	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public String getAccessionNumber() {
		return accessionNumber;
	}
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public DicomInstanceMessage takeInstanceQueue() throws InterruptedException {
		return instanceQueue.take();
	}
	public void putInstanceQueue(DicomInstanceMessage dicomMessage) throws InterruptedException {
		instanceQueue.put(dicomMessage);
	}
	public void putFirstInstanceQueue(DicomInstanceMessage dicomMessage) throws InterruptedException {
		instanceQueue.putFirst(dicomMessage);
	}
	public Integer getProcessedStatus() {
		return processedStatus;
	}
	public void setProcessedStatus(Integer processedStatus) {
		this.processedStatus = processedStatus;
	}
	
	
	
	
	
	
	
	
	
}










