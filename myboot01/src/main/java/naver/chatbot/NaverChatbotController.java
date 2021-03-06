package naver.chatbot;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.myboot01.UploadVO;

@Controller
public class NaverChatbotController {

	@Autowired
	NaverChatbotService chatbotservice;
	@Autowired
	@Qualifier("chatbotspeechService")
	NaverSpeechService speechservice; //NaverSpeechService가 두개인데 어떤것을 사용할것인가? Qualifier
	@Autowired
	@Qualifier("chatbotvoiceService")
	NaverVoiceService voiceservice; //텍스트 - 음성답변
	
	@RequestMapping("/chatbotstart")
	public String chatbotstart() {
		return "/naver/chatbot/chatbotstart";
	}
	
	@RequestMapping("/chatbot")
	@ResponseBody
	public String chatbot(String message) {
		String event = null;
		if(message == "") {
			event = "open";
		}
		else {
			event = "send";
		}
		
		String response = chatbotservice.test(message, event);
		return response; //{....}
	}
	// 질문 받아서 네이버 서버 호출, 답변 내용 리턴
	
	// 음성파일 업로드
	@RequestMapping(value="/mp3upload", method=RequestMethod.POST)
	@ResponseBody
	public String uploadresult(MultipartFile file) throws IOException{
		
		//System.out.println(file.getOriginalFilename());
		//업로드한 파일명 추출 (원본파일)
		String filename = file.getOriginalFilename();
		// 서버 저장 경로 설정
		String savePath = "c:/kdigital/images/"; //업로드 폴더
		// path+name
		File savefile = new File(savePath+filename);

		//서버 저장
		file.transferTo(savefile);
		//return "/upload/uploadresult"; //기존값
		return "잘받았습니다"; //python 테스트
	}
	
	//STT 음성 질문 요청
	@RequestMapping(value="/chatbotspeech")
	@ResponseBody
	public String speech(String mp3file, String lang) {
		String result = speechservice.test(mp3file);
		return result; //음성데이터를 텍스트로 변환한 결과
	}
	
	//TTS 음성 답변 응답
	@RequestMapping(value="/chatbotvoice")
	@ResponseBody
	public String voice(String text, String speaker) {
		String result = voiceservice.test(text, speaker);
		return result; //return *.mp3 file
	}
	
	
}
