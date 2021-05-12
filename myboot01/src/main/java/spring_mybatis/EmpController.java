package spring_mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
	@RequestMapping("/emplistpage")
	public ModelAndView getEmpList(int page){ //page 변수에 전달된 값이 보여줄 페이지, 한페이지당 출력갯수 10개, 입사일이 빠른 사원부터 출력
		//mybatis SqlSession -- EmpDAO -- EmpService
		int rownum [] = new int[2];
		rownum[0] = (page-1)*10+1;
		rownum[1] = page*10;
		List<EmpVO> list = service.getPagingEmp(rownum);
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
	public ModelAndView getOneEmp(int id) {
		EmpVO vo = service.getOneEmp(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("detail", vo);
		mv.setViewName("/mybatis/empdetail");
		return mv;
	}
	
	//추가 폼 출력, 사원정보 입력 - db insert - emplist view를 보여줌
	// employ_id, email - 중복 X / last_name not null / job_id, department_id, manager_id = 외래키 제약조건
	@RequestMapping(value="/empadd", method=RequestMethod.GET)
	public String addEmp() {
		return "/mybatis/addform";
	}
	
	/*
	<form action="/empadd" method="post">
	사번<input type=text name="employee_id"><br>
	성<input type=text name="last_name"><br>
	이름<input type=text name="first_name"><br>
	이메일<input type=text name="email"><br>
	직종<input type=text name="job_id"><br>
	<input type=submit value="사원등록"><br>
	 */
	
	@RequestMapping(value="/empadd", method=RequestMethod.POST)
	public String addEmp2(EmpVO vo) { //매개변수를 모두 나열해도 되고, VO를 선언해도된다.
		//System.out.println(vo.toString());
		service.registerEmp(vo);
		//return "/mybatis/emplist";
		return "redirect:/emplist";
	}
	
	//수정 - 미리 폼에 입력값이 설정된 상태, 
	@RequestMapping(value="/empmodify", method=RequestMethod.GET)
	public ModelAndView modifyEmp(int id) { 
		ModelAndView mv = new ModelAndView();
		EmpVO vo = service.getOneEmp(id);
		mv.addObject("emp", vo);
		mv.setViewName("/mybatis/modifyform");
		return mv;
	}
	
/*	//수정 처리
	@RequestMapping(value="/empmodify", method=RequestMethod.POST)
	public String modifyProcessEmp(EmpVO vo) {
		ModelAndView mv = new ModelAndView();
		service.modifyEmp(vo);
		return "redirect:/emplist";
	}
*/	
	//삭제
	@RequestMapping(value="/empdelete", method=RequestMethod.GET)
	public String deleteEmp(int id) {
		service.deleteEmp(id);
		return "redirect:/emplist";
	}
}
