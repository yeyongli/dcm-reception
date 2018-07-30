package com.kurumi.reception.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.reception.util.SuidToHash;


@RequestMapping("/picture")
@Controller
public class PictureController {
	private Logger log = LoggerFactory.getLogger(PictureController.class);
	
	@RequestMapping("/createFlow")
	@ResponseBody
	public void createFlow(String sessionId, String studyUid, String seriesUid, String instanceUid,
			HttpServletRequest request, HttpServletResponse response) {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			String pictureDir = "d:/dicom/dicom_picture" + "/" + SuidToHash.getHash(sessionId) + "/" + 
					sessionId + "/" + studyUid + "/" + seriesUid;
			String picturePath = pictureDir + "/" +instanceUid + ".jpg";
			
			//从本地缓存读取 
			is = new FileInputStream(picturePath);
			os = response.getOutputStream();
			int length = 0;
			byte[] buffer = new byte[1024*8];
			while((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
