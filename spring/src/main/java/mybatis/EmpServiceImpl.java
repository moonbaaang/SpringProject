package mybatis;

import java.util.List;
import java.util.Map;

public class EmpServiceImpl implements EmpService{
	EmpDAO dao;

	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	public EmpVO getOneEmp(int employee_id) {
		return dao.getOneEmp(employee_id);
	}
	
	public List<EmpVO> getAllEmp(){
		return dao.getAllEmp();
	}


	public void insertEmp(EmpVO vo) {
		dao.insertEmp(vo);
		
	}

	@Override
	public void updateEmp(EmpVO vo) {
		dao.updateEmp(vo);
		
	}
	
	public void deleteEmp(int employee_id) {
		dao.deleteEmp(employee_id);
		
	}

	@Override
	public List<EmpVO> getPageEmp(int[] nums) {
		return dao.getPageEmp(nums);
	}
	
	public void insertEmp2(EmpVO vo) {
		dao.insertEmp2(vo);
		
	}

	@Override
	public List<EmpVO> getEmpDept(List<Integer> deptList) {
		return dao.getEmpDept(deptList);
	}

	@Override
	public void updateEmpMap(Map<String, String> map) {
		dao.updateEmpMap(map);
		
	}
	
	
	
}
