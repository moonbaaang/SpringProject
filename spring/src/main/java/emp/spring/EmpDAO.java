package emp.spring;

public class EmpDAO {
	void insertEmp(VO vo){ 
		
		if(vo instanceof EmpVO) {
			EmpVO e = (EmpVO)vo;
			System.out.println(e.getId()+":"+e.getName()+":"+e.getSalary());
		}
		else if (vo instanceof MemberVO){
			MemberVO m = (MemberVO)vo;
			System.out.println(m.getId()+":"+m.getName());
		}
		
		System.out.println("db 등록 완료");
	}
}
