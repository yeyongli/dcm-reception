import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.kurumi.reception.util.JsonUtils;
import com.kurumi.reception.util.ReceiveMSMQUtil;
import com.kurumi.reception.util.SendMSMQUtil;

public class SessionUrlInfoTest {
	
	@Test
	public void test1() {
		String msmqAddress = "direct=tcp:192.168.21.85\\private$\\dicomInstanceQueue";
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
		System.out.println("json字符串的值为--------------" + jsonStr);
		SessionMessage s1 = JsonUtils.jsonToPojo(jsonStr, SessionMessage.class);
		System.out.println(s1.getSessionId());
		for(InstanceUrl insta : s1.getInstanceUrlList()) {
			System.out.println(insta);
		}
		
		SendMSMQUtil.putMessageQueue(msmqAddress, "session", 
				 jsonStr, UUID.randomUUID().toString());
	}
	
	@Test
	public void test2() {
		String fullNameMsmq = "direct=tcp:192.168.21.85\\private$\\dicomInstanceQueue";
		Map<String, String> map = ReceiveMSMQUtil.takeMessageQueue(fullNameMsmq);
		String jsonStr = map.get("stringBody");
		SessionMessage s1 = JsonUtils.jsonToPojo(jsonStr, SessionMessage.class);
		System.out.println(s1.getSessionId());
		for(InstanceUrl insta : s1.getInstanceUrlList()) {
			System.out.println(insta);
		}
		
		
	}
	
	
	
}



