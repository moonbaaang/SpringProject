package spring_mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Mapper //@MapperScan (매퍼를 붙이면 설정을 해줘야함)
@Repository("dao") //EmpDAO dao = new EmpDAO() Component
public interface EmpDAO {
	
	public EmpVO getOneEmp(int employee_id);
	
	public List<EmpVO> getAllEmp();
	 
	public List<EmpVO> getPagingEmp(int[] page); //<mapper namespace "xxx.EmpDAO> 페이징처리
	
	public EmpVO checkEmp(EmpVO vo); //사번, 이메일 중복 검사
	public String checkJob(EmpVO vo); //job_id 존재여부
	public void insertEmp(EmpVO vo); // 결과 조회 후 insert
	
	public void modifyEmp(EmpVO vo); // 내용 수정
	
	public void deleteEmp(int employee_id);
	
//	  public void insertEmp(EmpVO vo) { session.insert("emp.insertEmp", vo); }
//	  
//	  public void updateEmp(EmpVO vo) { session.update("emp.updateEmp", vo); }
//	  
//	  public void deleteEmp(int employee_id) { session.delete("emp.deleteEmp",
//	  employee_id); }
//	  

//	  
//	  public void insertEmp2(EmpVO vo) { session.insert("emp.insertEmp2", vo); }
//	  
//	  public List<EmpVO> getEmpDept(List<Integer> deptList){ return
//	  session.selectList("emp.selectwithlist", deptList); }
//	  
//	  public void updateEmpMap(Map<String, String> map) {
//	  session.update("emp.updatewithmap", map);
//	  
//	  }
	 
}
