package com.multi.kdigitalboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController2 {
	@RequestMapping("/HH1440") // 포트번호까지만 친것과 동일함
	public String hh1440() {
		return "HH__1440__";
	}

}
