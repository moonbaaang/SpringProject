package member;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("member/member.xml");
		
		MemberVO vo = factory.getBean("vo", MemberVO.class);
		MemberVO vo2 = factory.getBean("vo2", MemberVO.class);
		
		MemberDAO dao = factory.getBean("dao", MemberDAO.class);
		//dao.setMemberVO(vo); xml 태그가 이와같은 역할 대신함 <property name="memberVO" ref="vo" /> 
		//
		
		boolean login = dao.selectMember();
		if(login) {
			System.out.println("정상로그인 사용자");
		}
		dao.insertMember();
	}

}
