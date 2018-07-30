package com.kurumi.reception.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
	
	@Value("${full.name.msmq.patientQueue}")
	private String FULL_NAME_MSMQ_PATIENTQUEUE;
	
	/**
	 * instanceTag的值添加到msmq队列里面
	 * 
	 * @param file
	 * @param studyUid
	 * @param seriesUid
	 * @param instanceUid
	 * @return
	 */
	@PostMapping
	@RequestMapping("/handlerInstanceTag")
	public Result handlerInstanceTag(HttpServletRequest request) {
		try {
			log.info("-------begin handlerInstanceTag方法: 接收instanceTag的值添加到MSQM--------");
			Map <String, String> map = new HashMap<String, String>();
			map.put("sessionId", request.getParameter("sessionId"));
			map.put("studyInstanceUID", request.getParameter("studyInstanceUID"));
			map.put("seriesInstanceUID", request.getParameter("seriesInstanceUID"));
			map.put("sOPInstanceUID", request.getParameter("sOPInstanceUID"));
			map.put("instanceNumber", request.getParameter("instanceNumber"));
			map.put("characterSet", request.getParameter("characterSet"));
			map.put("transferSyntaxUID", request.getParameter("transferSyntaxUID"));
			map.put("sOPClassUID", request.getParameter("sOPClassUID"));
			map.put("modality", request.getParameter("modality"));
			map.put("seriesNumber", request.getParameter("seriesNumber"));
			map.put("dcmFileStorageLocation", request.getParameter("dcmFileStorageLocation"));
			map.put("studyID", request.getParameter("studyID"));
			map.put("studyDate", request.getParameter("studyDate"));
			map.put("studyTime", request.getParameter("studyTime"));
			map.put("accessionNumber", request.getParameter("accessionNumber"));
			map.put("numberFrame", request.getParameter("numberFrame"));
			 
			String jsonStr = JsonUtils.objectToJson(map);
			//put data enter queue 
			 SendMSMQUtil.putMessageQueue(FULL_NAME_MSMQ, "instance", jsonStr, UUID.randomUUID().toString());
			 
			return new Result(true, "接收instance信息成功!", null, 200);
		} catch (Exception e) {
			log.error("-------handlerInstanceTag方法: 接收instanceTag的值添加到MSQM异常: --------" + ExceptionUtil.getStackTrace(e));
			return new Result(false, "接收instance信息异常!" + ExceptionUtil.getStackTrace(e), null, 500);
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
			log.info("--------------begin handlePatientInfo方法: 患者信息添加到MSMQ队列里面-------------");
			String patientJson = JsonUtils.objectToJson(patient);
			
			if (patient != null) {
				SendMSMQUtil.putMessageQueue(FULL_NAME_MSMQ_PATIENTQUEUE, "patientQueue", patientJson, UUID.randomUUID().toString());
				return new Result(true, "处理patient信息成功!", null, 200);
			}
			
			log.warn("--------------begin handlePatientInfo方法: 患者信息添加到MSMQ队列里面------------- 接收到patient的参数为空!");
			
			return new Result(true, "patient参数为空!", null, 200);
		} catch (Exception e) {
			log.error("------ handlePatientInfo方法, 患者信息添加到MSMQ队列里面异常-----------" + ExceptionUtil.getStackTrace(e));
			return new Result(false, "处理patient信息失败!", null, 500);
		}
	}

	/**
	 * 接收session json字符串信息添加到MSMQ队列里面
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/handleSessionInfo")
	@ResponseBody
	public Result handleSessionInfo(HttpServletRequest request) {
		try {
			log.info("-------begin handleSessionInfo方法: 接收session json字符串信息添加到MSMQ队列里面----------");
			String sessionList = request.getParameter("sessionList");
			
			if(StringUtils.isNotBlank(sessionList)) {
				SendMSMQUtil.putMessageQueue(FULL_NAME_MSMQ, "session", 
						 sessionList, UUID.randomUUID().toString());
				return new Result(true, "处理session信息成功!", null, 200);
			}
			
			log.warn("--------handleSessionInfo方法, 接收session json字符串信息添加到MSMQ队列里面------- 接收到sessoinList参数为空");
			
			return new Result(false, "sessionList参数为空!", null, -1);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("------handleSessionInfo方法, 接收session json字符串信息添加到MSMQ队列里面异常----------:" + ExceptionUtil.getStackTrace(e));
			return new Result(false, "处理session信息失败!", null, 500);
		}
	}
	
	
	
	
	
}










