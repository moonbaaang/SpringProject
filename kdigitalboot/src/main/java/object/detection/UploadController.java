package object.detection;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	@Autowired
	NaverObjectDetectionService odservice;
	
	@RequestMapping(value="/projecttest", method=RequestMethod.GET)
	public String uploadform() {
		return "/projecttest/uploadform";
	}
	
	@RequestMapping(value="/projecttest", method=RequestMethod.POST)
	public ModelAndView uploadresult(@ModelAttribute("vo") UploadVO vo) 
			throws IllegalStateException, IOException{
		ModelAndView mv = new ModelAndView();
		mv.addObject("uploadvo", vo);
		MultipartFile multipartfile1 = vo.getFile1();
		
		System.out.println(multipartfile1.getOriginalFilename());
		
		
		String filename1 = multipartfile1.getOriginalFilename();
		String savePath="c:/upload/";
		
		File file1 = new File(savePath + filename1);
		multipartfile1.transferTo(file1);
		String result = odservice.test(filename1);
		mv.addObject("image", filename1);
		mv.addObject("odResult", result);
		mv.setViewName("/projecttest/od");
		
		return mv;
	}
}
