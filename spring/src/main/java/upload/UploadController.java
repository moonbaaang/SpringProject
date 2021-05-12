package upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
//servlet-context.xml에 <context:component-scan base-package="upload" /> 추가해야한다.

@Controller
public class UploadController {
	
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-","").substring(0,10);
	}
	
	
	@RequestMapping(value="/fileupload", method=RequestMethod.GET)
	public String uploadform() {
		return "/upload/uploadform";
		//view를 보여주기 위한 표현
	}
	@RequestMapping(value="/fileupload", method=RequestMethod.POST)
	public String uploadresult(@ModelAttribute("vo") UploadVO vo) throws IOException{ // name , description, file1, file2 전송
		//@ModelAttribute("vo") 라는 의미?
		//ModelAndView mv = new ModelAndView();
		//mv.addObject("", vo);  와 같음
		
		//업로드한 파일 객체
		MultipartFile multipartfile1 = vo.getFile1();
		MultipartFile multipartfile2 = vo.getFile2();
		
		//System.out.println(multipartfile1.getOriginalFilename()); //클라이언트가 전송했던 파일 이름을 출력
		
		//업로드한 파일명 추출
		String filename1 = multipartfile1.getOriginalFilename();
		String filename2 = multipartfile2.getOriginalFilename();
		
		// 서버 저장 경로 설정
		String savePath = "c:/upload/"; //업로드 폴더
		
		// 중복파일처리1 : 
		// api : 랜덤암호화변경이름
		// a.txt --> 123wsdjhfckdjf.txt 파일 이름을 임의 문자열로 변경
		String path []= filename1.split("[.]");
		//for(String s : path) System.out.println("==="+s);
		//String ext1 = "."+filename1.split("[.]")[filename1.split("[.]").length-1];
		String ext1 = filename1.substring(filename1.lastIndexOf("."));
		
		//String ext2 = "."+filename2.split("[.]")[filename2.split("[.]").length-1];
		String ext2 = filename2.substring(filename2.lastIndexOf("."));	
		
		System.out.println(ext1+":"+ext2);
		filename1 = getUuid() + ext1;
		filename2 = getUuid() + ext2;

		
		// path+name
		File file1 = new File(savePath+filename1);
		File file2 = new File(savePath+filename2);
		
		//서버 저장
		multipartfile1.transferTo(file1);
		multipartfile2.transferTo(file2);
		
		return "/upload/uploadresult"; 
	}
}
