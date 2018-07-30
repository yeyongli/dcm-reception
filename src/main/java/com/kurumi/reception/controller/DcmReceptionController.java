package com.kurumi.reception.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kurumi.reception.entity.Patient;
import com.kurumi.reception.util.ExceptionUtil;
import com.kurumi.reception.util.JsonUtils;
import com.kurumi.reception.util.Result;
import com.kurumi.reception.util.SendMSMQUtil;

@RequestMapping("/dcmReception")
@Controller
public class DcmReceptionController {
	private static final Logger log = LoggerFactory.getLogger(DcmReceptionController.class);
	
	@Value("${patient.json}")
	private String PATIENT_JSON;

	@Value("${a-check.json}")
	private String A_CHECK_JSON;
	
	@Value("${dcm.file.storage.location}")
	private String DCM_FILE_STORAGE_LOCATION;
	
	@Value("${full.name.msmq}")
	private String FULL_NAME_MSMQ;
	
	/**
	 * 处理dcm文件
	 * 
	 * @param file
	 * @param studyUid
	 * @param seriesUid
	 * @param instanceUid
	 * @return
	 */
	@PostMapping
	@RequestMapping("/handlerDcmTag")
	public Result handlerDcmTag(MultipartFile file, HttpServletRequest request) {
		try {
			 StringBuffer sb = new StringBuffer();
			 sb.append("sessionId="+request.getParameter("sessionId")+"\r\n");
			 sb.append("studyInstanceUID="+request.getParameter("studyInstanceUID")+"\r\n");
			 sb.append("seriesInstanceUID="+request.getParameter("seriesInstanceUID")+"\r\n");
			 sb.append("sOPInstanceUID="+request.getParameter("sOPInstanceUID")+"\r\n");
			 sb.append("instanceNumber="+request.getParameter("instanceNumber")+"\r\n");
			 sb.append("characterSet="+request.getParameter("characterSet")+"\r\n");
			 sb.append("transferSyntaxUID="+request.getParameter("transferSyntaxUID")+"\r\n");
			 sb.append("sOPClassUID="+request.getParameter("sOPClassUID")+"\r\n");
			 sb.append("modality="+request.getParameter("modality")+"\r\n");
			 sb.append("seriesNumber="+request.getParameter("seriesNumber")+"\r\n");
			 sb.append("dcmFileStorageLocation="+request.getParameter("dcmFileStorageLocation")+"\r\n");
			 sb.append("studyID="+request.getParameter("studyID")+"\r\n");
			 sb.append("studyDate="+request.getParameter("studyDate")+"\r\n");
			 sb.append("studyTime="+request.getParameter("studyTime")+"\r\n");
			 sb.append("accessionNumber="+request.getParameter("accessionNumber"));
			 
			//put data enter queue 
			 SendMSMQUtil.putMessageQueue(FULL_NAME_MSMQ, "instance", 
					 sb.toString(), UUID.randomUUID().toString());
			 
			return new Result(true, "接收dicom信息成功!", null, 200);
		} catch (Exception e) {
			return new Result(false, "接收dicom信息异常!" + ExceptionUtil.getStackTrace(e), null, 500);
		}
		
	}
	
	/**
	 * 患者信息添加到MSMQ队列里面
	 * 
	 * @param patient
	 * @param request
	 * @return
	 */
	@PostMapping("/handlePatientInfo")
	@ResponseBody
	public Result handlePatientInfo(Patient patient, HttpServletRequest request) {
		try {
			String patientJson = JsonUtils.objectToJson(patient);
			SendMSMQUtil.putMessageQueue(FULL_NAME_MSMQ, "patientQueue", patientJson, UUID.randomUUID().toString());
			return new Result(true, "处理patient信息成功!", null, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "处理patient信息失败!", null, 500);
		}
	}

	/**
	 * 接收session列表信息
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/handleSessionInfo")
	@ResponseBody
	public Result handleSessionInfo(HttpServletRequest request) {
		try {
			String sessionList = request.getParameter("sessionList");
			SendMSMQUtil.putMessageQueue(FULL_NAME_MSMQ, "session", 
					 sessionList, UUID.randomUUID().toString());
			return new Result(true, "处理session信息成功!", null, 200);
		} catch (Exception e) {
			return new Result(false, "处理session信息失败!", null, 500);
		}
	}
	
	
	
	
	
	
	
	
	
}










