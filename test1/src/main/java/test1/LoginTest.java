package test1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginTest {
	@RequestMapping(value="/logintest", method=RequestMethod.GET)
	public String loginform() { 
		return "test1";
	}

	@RequestMapping(value="/logintest",method=RequestMethod.POST,
			produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public LoginVO loginform(String id, String pw) {
		LoginVO vo = new LoginVO();
		vo.setId(id);
		vo.setPw(pw);	
		
		return vo;
	}

	
}