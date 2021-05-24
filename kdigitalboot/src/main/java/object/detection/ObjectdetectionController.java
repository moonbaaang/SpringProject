package object.detection;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ObjectdetectionController {

	@Autowired
	NaverObjectDetectionService service;
	
	//Object detection
		@RequestMapping("/odinput")
		public ModelAndView objectdetectioninput() {
			File f = new File("C:\\kdigital\\images");
			String[] filelist = f.list();
			ModelAndView mv = new ModelAndView();
			mv.addObject("filelist", filelist);
			mv.setViewName("/naver/od");
			return mv;
		}
		 
		@RequestMapping(value="/odinput", method=RequestMethod.GET)
		public ModelAndView objectdetection(String image) {
			String result = service.test(image); 
			ModelAndView mv = new ModelAndView();
			mv.addObject("objectdetectionResult", result);
			mv.setViewName("/naver/odinput"); 
			return mv;
		}
	
}
