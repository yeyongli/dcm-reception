

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.kurumi.reception.util.HttpClientUtil;
import com.kurumi.reception.util.JsonUtils;


public class HttpClientUtilTest {

	@Test
	public void test1() {
		Map<String, String> params = new HashMap<String, String>();
		SessionMessage session = new SessionMessage();
		session.setSessionId(UUID.randomUUID().toString());
		
		List<InstanceUrl> instances = new ArrayList<InstanceUrl>();
		InstanceUrl in = new InstanceUrl();
		in.setInstanceNumber(1);
		in.setModality("ct");
		in.setSeriesInstanceUID("234234324");
		in.setSeriesNumber(1);
		in.setsOPInstanceUID("1232323");
		in.setStudyDate("2012-10-12");
		in.setStudyInstanceUID("12432222222");
		instances.add(in);
		session.setInstanceUrlList(instances);
		String jsonStr = JsonUtils.objectToJson(session);
		params.put("sessionList", jsonStr);
		String url = "http://localhost:28080/reception/dcmReception/handleSessionInfo";
		HttpClientUtil.doPost(url, params);
	}
	
	@Test
	public void test2() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("patientKey", "11");
		params.put("patientID", "1111");
		params.put("patientsName", "张三");
		params.put("patientsNameCn", "zhangshang");
		params.put("patientsBirthDate", "2015-10-12");
		params.put("patientsSex", "男");
		params.put("patientsAge", "11");
		params.put("patientsHeight", "22");
		params.put("patientsWeight", "22");
		params.put("studyInstanceUID", "122sfsf");
		params.put("sessionId", "12323");
		String url = "http://localhost:28080/reception/dcmReception/handlePatientInfo";
		
		HttpClientUtil.doPost(url, params);
	}
	
	@Test
	public void test3() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("sessionId", "123456");
		params.put("studyInstanceUID", UUID.randomUUID().toString());
		params.put("seriesInstanceUID", UUID.randomUUID().toString());
		params.put("sOPInstanceUID", UUID.randomUUID().toString());
		params.put("instanceNumber", "1");
		params.put("characterSet", "utf-8");
		params.put("transferSyntaxUID", "abs");
		params.put("sOPClassUID", "less");
		params.put("modality", "ct");
		params.put("seriesNumber", "11");
		params.put("dcmFileStorageLocation", "C:/Users/h2oco2/Desktop/R3N4W4F_");
		params.put("studyID", "abc");
		params.put("studyDate", "2017-03-10");
		params.put("studyTime", "2017-03-10");
		params.put("accessionNumber", "1232323");
		params.put("numberFrame", "1");
		
		String url = "http://localhost:28080/reception/dcmReception/handlerInstanceTag";
		HttpClientUtil.doPost(url, params);
	}
	
	
	
	
	
	
	

}




