import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurumi.reception.util.JsonUtils;
import com.sun.javafx.collections.MappingChange.Map;

public class SessionUrlInfoTest {
	public static void main(String[] args) {
		SessionMessage session = new SessionMessage();
		
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
	}
	
	
	
}



