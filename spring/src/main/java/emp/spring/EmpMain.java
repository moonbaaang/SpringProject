package emp.spring;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class EmpMain {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("emp/spring/emp.xml");
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("eml.xml"));
		// ---이 되는 이유 Derecated = 스프링에서 더이상 이러한 api는 사용하지 않도록 권고
		
		VO vo = factory.getBean("vo", VO.class);
		VO vo2 = factory.getBean("vo2", VO.class);
		EmpDAO dao = factory.getBean("dao", EmpDAO.class);
		
		
		dao.insertEmp(vo); //EmpVO(int string double) 생성자 객체 전달
		dao.insertEmp(vo2);

		
		System.out.println("등록이 완료되었습니다.");
	}

}
