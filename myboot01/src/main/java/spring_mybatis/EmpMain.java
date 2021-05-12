package spring_mybatis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpMain {

	public static void main(String args[]) throws IOException {
		
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring_mybatis/mybatis_spring.xml");
		EmpService service = factory.getBean("service", EmpService.class);
		//System.out.println(session.getConnection());
		//System.out.println(session);

		
		EmpVO vo = service.getOneEmp(200);
		System.out.println(vo);
		
		//한번에 읽어오려면 toString 메서드 
		//EmpVO vo = session.selectOne("emp.oneEmp");
		//System.out.println(vo.getEmployee_id());
		//System.out.println(vo.getFirst_name());
	
		
//		List<EmpVO> list = service.getAllEmp();
//		for(EmpVO vo: list) {
//			System.out.println(vo);
//		}
		
		
		//insert test
/*		EmpVO vo = new EmpVO
		(300, "사원", "김", "kim@a.com", "010222333", "IT_PROG", 100, 1000, 50);
		service.insertEmp(vo);
		System.out.println("1명 사원 저장 완료");
		
		EmpVO vo2 = service.getOneEmp(300);
		System.out.println(vo2);
		
		// 300 사번의 사원 이름을 대리로 변경
		EmpVO vo3 = new EmpVO();
		vo3.setEmployee_id(300);
		vo3.setFirst_name("대리");
		service.updateEmp(vo3);
		
		System.out.println("1명 사원 수정 완료");


		int employee_id = 300;
		service.deleteEmp(employee_id);
*/		

		// 페이징 처리
/*		int[] nums = {11,20};
		List<EmpVO> list = service.getPageEmp(nums);
		for(EmpVO vo : list) {
			System.out.println(vo);
		}
*/
		// 시퀀스값 전달, -1값은 의미가 없다
/*		EmpVO vo = new EmpVO
		(-1, "사원", "김", "kim2@a.com", "010222233", "IT_PROG", 100, 300, 50);
		service.insertEmp2(vo);
		System.out.println("1명 사원 저장 완료");
				
		EmpVO vo2 = service.getOneEmp(300);
		System.out.println(vo2);
*/		
/*		EmpVO vo = new EmpVO();
		//vo.setEmployee_id(2000);
		vo.setFirst_name("과장");
		vo.setLast_name("김");//not null
		vo.setJob_id("IT_PROG");//jobs테이블 참조
		vo.setDepartment_id(100);//departments테이블 참조
		vo.setEmail("kim5@kitri.com");//unique
		vo.setPhone_number("0102273022");
		vo.setSalary(1000);
		service.insertEmp2(vo);
		System.out.println("===insert seq 완료===");
*/
/*		List<Integer> deptList = new ArrayList<Integer>();
		deptList.add(10);
		deptList.add(50);
		deptList.add(80);
		deptList.add(100);
		List<EmpVO> list = service.getEmpDept(deptList);
		for(EmpVO vo : list) {
			System.out.println(
					vo.getEmployee_id()+":"+vo.getFirst_name()+":"+vo.getDepartment_id());
*/
/*
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept", "50");
		map.put("email", "abab@a.com");
		map.put("id", "134");
		service.updateEmpMap(map);
		System.out.println("업데이트 수행 완료");
		
		*/
	}
}
