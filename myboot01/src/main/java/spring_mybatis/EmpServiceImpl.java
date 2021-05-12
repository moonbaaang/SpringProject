package spring_mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpDAO dao;

	public EmpVO getOneEmp(int employee_id) {
		return dao.getOneEmp(employee_id);
	}
	
	public List<EmpVO> getAllEmp(){ 
		return dao.getAllEmp(); 
	}
	
	 @Override
	 public List<EmpVO> getPagingEmp(int[] page) {
		 return dao.getPagingEmp(page); 
	}

	 // 사번, 이메일 중복 검사 - 조회 결과 존재하면 사번이나 이메일 등록 불가			
	 // job_id > jobs table에 존재하는 값만 가능 , 존재 여부 확인 - 존재 시 등록 가능 직종		
	 // insert 수행					
	@Override
	public void registerEmp(EmpVO vo) { //사원등록 서비스 기능
		// last_name > not null 여부 조회
		//System.out.println("성 = "+vo.getLast_name());
		if(vo.getLast_name()!=null) {
			EmpVO vo2 = dao.checkEmp(vo);
			String job_id = dao.checkJob(vo);
			//System.out.println("vo2 = " +vo2+", job_id = "+ job_id);
			if(vo2 == null && job_id !=null) {
				dao.insertEmp(vo);
			}
		}
	}

	@Override
	public void deleteEmp(int employee_id) {
		dao.deleteEmp(employee_id);
	}

	@Override
	public void modifyEmp(EmpVO vo) {
			dao.modifyEmp(vo);
	}
		
	
	
	
	
} 
//	  public void insertEmp(EmpVO vo) { dao.insertEmp(vo);
//	  
//	  }
//	  
//	  @Override public void updateEmp(EmpVO vo) { dao.updateEmp(vo);
//	  
//	  }
//	  
//	  public void deleteEmp(int employee_id) { dao.deleteEmp(employee_id);
//	  
//	  }
//	  

//	  
//	  public void insertEmp2(EmpVO vo) { dao.insertEmp2(vo);
//	  
//	  }
//	  
//	  @Override public List<EmpVO> getEmpDept(List<Integer> deptList) { return
//	  dao.getEmpDept(deptList); }
//	  
//	  @Override public void updateEmpMap(Map<String, String> map) {
//	  dao.updateEmpMap(map);
//	  
//	  }
//	 

