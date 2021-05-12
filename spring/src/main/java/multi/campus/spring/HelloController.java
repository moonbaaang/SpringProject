package multi.campus.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request, 
			HttpServletResponse response) {
		//request.setAttribute("model", "Hello Spring~");
		//return "hello.jsp";
		ModelAndView mv = new ModelAndView();
		mv.addObject("model", "hello spring~");
		mv.addObject("model2", "hello spring2~"); //응답할 데이터를 만들어준다.
		mv.setViewName("hello");
		return mv;
		
		
	}

}
