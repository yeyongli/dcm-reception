import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackTest {
	private Logger log = LoggerFactory.getLogger(LogBackTest.class);

	@Test
	public void test() {
		log.info("info信息------------");
		log.warn("warm信息---------");
		log.debug("debug信息-------");
		log.error("error信息----------");
	}
	
	

	

}
