package spring_mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpController {
	@Autowired
	EmpService service;

	//employee 테이블 전체 조회
	@RequestMapping("/emplist")
	public ModelAndView getEmpList(){
		//mybatis SqlSession -- EmpDAO -- EmpService
		List<EmpVO> list = service.getAllEmp();
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", list); //request.getAttribute("emplist") ${emplist}
		mv.setViewName("/mybatis/emplist");
		return mv;
	}
	
	// 클라이언트 입력 id 파라미터 = 100
	// url /empdetail
	//employee 테이블 1개 레코드 조회
	// model로 생성
	// /mybatis/empdetail.jsp
	// empdetail.jsp출력
	@RequestMapping("/empdetail")
	public ModelAndView getEmpDetail(int id) {
		EmpVO vo = service.getOneEmp(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("detail", vo);
		mv.setViewName("/mybatis/empdetail");
		return mv;
	}
	
	
	//추가 수정 삭제
}
