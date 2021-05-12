package emp;

public class EmpMain {

	public static void main(String[] args) {
		VO vo = new EmpVO();
		vo.setId(100);
		vo.setName("이사원");
//		((EMPVO)vo).setSalary(12302410.10);
		
		VO vo2 = new MemberVO();
		vo2.setId(200);
		vo2.setName("김회원");
//		vo2.setEmail("M@naver.com");
		EmpDAO dao = new EmpDAO();
		dao.insertEmp(vo); //Spring으로부터 전달
		dao.insertEmp(vo2); 
		//주입 (EmpVO 객체에서 MemberVO 변경 외부전달 (의존성 주입 Dependency Injection)
		
		System.out.println("회원등록을 마쳤습니다.");
	}

}
