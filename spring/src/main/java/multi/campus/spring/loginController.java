package multi.campus.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {
	//1. 로그인 폼 출력 url 이 들어오면 loginform메소드 실행 get방식, 암호입력 시 post방식
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginform() {
		//모델값없다
		return "loginform"; //뷰 이름 리턴
	}
	// 2. 입력내용 로그인처리결과 출력
	@RequestMapping(value="/login", method=RequestMethod.POST)
		public ModelAndView loginresult(@ModelAttribute LoginVO vo) {

			ModelAndView mv = new ModelAndView();
			System.out.println(vo.getId()+":"+vo.getPw());
			if(vo.getId().equals("spring")&& vo.getPw().equals("work")) {
				mv.addObject("result", "정상 로그인 사용자");
			}
			else {
				mv.addObject("result", "아이디 암호를 다시 입력하세요.");
			}
			mv.setViewName("loginresult");
			return mv;
	}
}


/*
 *	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public ModelAndView loginform() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginform"); ///web-inf/views/loginform.jsp
		return mv; 
 * 
 *
 * 	@RequestMapping(value="/login", method=RequestMethod.POST) //("https://127.0.0.1:9090/spring/loginresult")
		public ModelAndView loginresult(HttpServletRequest request) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			ModelAndView mv = new ModelAndView();
			if(id.equals("spring")&& pw.equals("work")) {
				mv.addObject("result", "정상 로그인 사용자");
			}
			else {
				mv.addObject("result", "아이디 암호를 다시 입력하세요.");
			}
			mv.setViewName("loginresult");
			return mv;
	}
 
 *
 *
 *		public ModelAndView loginresult(@RequestParam(value="id", required = false, defaultValue = "spring") String id2,
				String pw) {
			// id, require=false > 반드시 입력할 값은 아님
 *
 */