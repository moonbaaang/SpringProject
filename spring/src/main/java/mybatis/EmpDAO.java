package mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDAO {
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public EmpVO getOneEmp(int employee_id) {
		EmpVO vo = session.selectOne("emp.oneEmp", employee_id);
		return vo;
	}
	
	//타입을 mybatis가 자동 결정한다.
	public List<EmpVO> getAllEmp(){
		List<EmpVO> list = session.selectList("emp.manyEmp");
		return list;
	}
	
	public void insertEmp(EmpVO vo) {
		session.insert("emp.insertEmp", vo);
	}
	
	public void updateEmp(EmpVO vo) {
		session.update("emp.updateEmp", vo);
	}
	
	public void deleteEmp(int employee_id) {
		session.delete("emp.deleteEmp", employee_id);
	}
	
	public List<EmpVO> getPageEmp(int[] nums){
		return session.selectList("emp.pageEmp", nums);
	}
	
	public void insertEmp2(EmpVO vo) {
		session.insert("emp.insertEmp2", vo);
	}
	
	public List<EmpVO> getEmpDept(List<Integer> deptList){
		return session.selectList("emp.selectwithlist", deptList);
	}
	
	public void updateEmpMap(Map<String, String> map) {
		session.update("emp.updatewithmap", map);
		
	}
}
