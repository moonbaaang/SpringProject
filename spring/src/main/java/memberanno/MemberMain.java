package memberanno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext
				("memberanno/member.xml");
		//<context... "memberanno" -- > @어노테이션을 찾아온다.
		// 아래 getBean의 "service"는 @Service("service")를 호출
		MemberService service = factory.getBean("service", MemberService.class);
		service.login();
		service.register();
	}

}
