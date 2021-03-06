package naver.cloud;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NaverController {

	@Autowired
	NaverFaceService service1; //유명인찾기
	@Autowired
	NaverFaceService2 service2; //얼굴인식
	@Autowired
	NaverOCRService ocrservice; // image > text
	@Autowired
	NaverObjectDetectionService objectdetectionservice; // Object detection service
	@Autowired
	NaverPoseService poseservice; //포즈 분석
	@Autowired
	NaverSpeechService speechservice;
	@Autowired
	NaverVoiceService voiceservice;
	
	// 닮은 유명인 찾기
	@RequestMapping("/faceinput")
	public ModelAndView faceinput() {
		// images 폴더를 File객체 생성
		// File 객체 list메소드 이용 파일이름만 배열 리턴
		// 리턴받은 배열을 모델로 생성 > filelist
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/faceinput");
		// faceinput.jsp 구현 / filelist 모델값을 a태그로 <a href="/face 호출하면서 파일이름 전달 ">파일명</a> 링크문자열
		return mv;
	}
	
	@RequestMapping(value="/face", method=RequestMethod.GET)
	public ModelAndView face(String image) {
		String result = service1.test(image);
		ModelAndView mv = new ModelAndView();
		mv.addObject("faceResult", result);
		mv.setViewName("/naver/face");
		return mv;
	} 
	
	
	// 얼굴 인식
	@RequestMapping("/faceinput2")
	public ModelAndView faceinput2() {
		// images 폴더를 File객체 생성
		// File 객체 list메소드 이용 파일이름만 배열 리턴
		// 리턴받은 배열을 모델로 생성 > filelist
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/faceinput2");
		// faceinput.jsp 구현 / filelist 모델값을 a태그로 <a href="/face 호출하면서 파일이름 전달 ">파일명</a> 링크문자열
		return mv;
	}
	 
	@RequestMapping(value="/face2", method=RequestMethod.GET)
	public ModelAndView face2(String image) {
		String result = service2.test(image);
		ModelAndView mv = new ModelAndView();
		mv.addObject("faceResult2", result);
		mv.setViewName("/naver/face2"); // 3 임시 변경
		return mv;
	}

	
	// 얼굴 인식 후 색상 변경
	@RequestMapping("/faceinput3")
	public ModelAndView faceinput3() {
		// images 폴더를 File객체 생성
		// File 객체 list메소드 이용 파일이름만 배열 리턴
		// 리턴받은 배열을 모델로 생성 > filelist
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/faceinput3");
		// faceinput.jsp 구현 / filelist 모델값을 a태그로 <a href="/face 호출하면서 파일이름 전달 ">파일명</a> 링크문자열
		return mv;
	}
	 
	@RequestMapping(value="/face3", method=RequestMethod.GET)
	public ModelAndView face3(String image) {
		String result = service2.test(image);
		ModelAndView mv = new ModelAndView();
		mv.addObject("faceResult3", result);
		mv.setViewName("/naver/face3"); 
		return mv;
	}
	
	@RequestMapping("/ocrinput")
	public ModelAndView ocrinput() {
		// images 폴더를 File객체 생성
		// File 객체 list메소드 이용 파일이름만 배열 리턴
		// 리턴받은 배열을 모델로 생성 > filelist
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/ocrinput");
		// faceinput.jsp 구현 / filelist 모델값을 a태그로 <a href="/ocr 호출하면서 파일이름 전달 ">파일명</a> 링크문자열
		return mv;
	}
	 
	@RequestMapping(value="/ocr", method=RequestMethod.GET)
	public ModelAndView ocr(String image) {
		String result = ocrservice.test(image); //이미지에서 텍스트 추출
		ModelAndView mv = new ModelAndView();
		mv.addObject("ocrResult", result);
		mv.setViewName("/naver/ocr"); // 임시 변경
		return mv;
	}
	
	//Object detection
	@RequestMapping("/objectdetectioninput")
	public ModelAndView objectdetectioninput() {
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/objectdetectioninput");
		return mv;
	}
	 
	@RequestMapping(value="/objectdetection", method=RequestMethod.GET)
	public ModelAndView objectdetection(String image) {
		String result = objectdetectionservice.test(image); 
		ModelAndView mv = new ModelAndView();
		mv.addObject("objectdetectionResult", result);
		mv.setViewName("/naver/objectdetection"); 
		return mv;
	}
	
	//테스트용 
	@RequestMapping(value="/objectdetectiontest", method=RequestMethod.GET)
	public ModelAndView objectdetectiontest(String image) {
		String result = objectdetectionservice.test(image); 
		ModelAndView mv = new ModelAndView();
		mv.addObject("objectdetectionResult", result);
		mv.setViewName("/naver/objectdetectionTEST"); 
		return mv;
	}
	
	
	// pose estimation
	@RequestMapping("/poseinput")
	public ModelAndView poseinput() {
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/poseinput");
		return mv;
	}
	 
	@RequestMapping(value="/pose", method=RequestMethod.GET)
	public ModelAndView pose(String image) {
		String result = poseservice.test(image); 
		ModelAndView mv = new ModelAndView();
		mv.addObject("poseResult", result);
		mv.setViewName("/naver/pose"); 
		return mv;
	}
	
	// CSR
	@RequestMapping("/speechinput")
	public ModelAndView speechinput() {
		File f = new File("C:\\kdigital\\images");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/speechinput"); //image=mp파일&lang=언어
		return mv;
	}
	 
	@RequestMapping(value="/speech", method=RequestMethod.GET)
	public ModelAndView speech(String image, String lang) { //@RequestParam("lang") String language (변수 이름을 다르게 했을 때)
		//lang 미선택시
		String result;
		if(lang==null) {
			result = speechservice.test(image);
		} else {//lang 선택시
			result = speechservice.test(image, lang); 
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("speechResult", result);
		mv.setViewName("/naver/speech"); 
		return mv;
	}
	
	// text > voice
	@RequestMapping("/voiceinput")
	public ModelAndView voiceinput() {
		File f = new File("C:\\kdigital\\images\\");
		String[] filelist = f.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/voiceinput"); //txt, speaker
		return mv;
	}
	 
	@RequestMapping(value="/voice", method=RequestMethod.GET)
	public ModelAndView voice(String image, String speaker) {
		String result;
		if(speaker==null) {
			result = voiceservice.test(image);			
		} else {
			result = voiceservice.test(image, speaker); 			
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("voiceResult", result); // 네이버 tts - *.mp3 
		mv.setViewName("/naver/voice"); 
		return mv;
	}
}
