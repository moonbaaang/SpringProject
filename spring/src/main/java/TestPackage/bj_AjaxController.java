package TestPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class bj_AjaxController {
	
	@Autowired
	
	@RequestMapping(value="/bj/ajax/login", method=RequestMethod.GET)
	public String loginform() {
		return "/bj/ajax/login";
	}
	
	@RequestMapping(value="/bj/ajax/login", method=RequestMethod.POST)
	public String loginresult(String id, String pw) {
		String result= null;
		

	}
	
}
