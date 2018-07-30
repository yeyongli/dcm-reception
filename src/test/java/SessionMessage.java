import java.util.List;

public class SessionMessage {
	private String sessionId;
	private List<InstanceUrl> instanceUrlList;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public List<InstanceUrl> getInstanceUrlList() {
		return instanceUrlList;
	}
	public void setInstanceUrlList(List<InstanceUrl> instanceUrlList) {
		this.instanceUrlList = instanceUrlList;
	}

	
}
