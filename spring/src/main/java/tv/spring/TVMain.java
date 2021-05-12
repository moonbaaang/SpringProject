package tv.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXNotRecognizedException;

public class TVMain {

	public static void main(String[] args) {
		//spring이 설정한 객체 주입 설정 파일 읽어옴
		ApplicationContext factory = 
				new ClassPathXmlApplicationContext("tv/spring/tv.xml");
		
		TV tv = factory.getBean("tv", TV.class);
		
		tv.powerOn();
		tv.soundUp();
		tv.soundDown();
		tv.powerOff();
		
		TV tv2 = factory.getBean("tv2", TV.class);
		tv2.powerOn();
		tv2.soundUp();
		tv2.soundDown();
		tv2.powerOff();
		
		
		tv = factory.getBean("tv", TV.class);
		// 생성자 호출은 한번밖에 보이지 않는다. (호출 한번에 singleton으로 취급) 단, scope=prototype로 변경시 계속 호출
		tv = factory.getBean("tv", TV.class);
		tv = factory.getBean("tv", TV.class);
	}

}
