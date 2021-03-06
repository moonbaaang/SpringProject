package naver.chatbot;

//네이버 음성합성 Open API 예제
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.stereotype.Service;

import naver.cloud.NaverService;

@Service("chatbotvoiceService")
public class NaverVoiceService implements NaverService{
	//1 화자 mijin기본값 설정
	//2 NaverController : voice 메소드 수정 - 스피커 미 선택시 voiceservice.test("")
	
	public String test(String txtfile) {
		return test(txtfile, "mijin");
	}
	
	public String test(String responsetext, String speaker) {
	 // mp3파일을 리턴값으로 받을 것
	 String returnfile = null;
	 String clientId = "nq848nfz2h";             // Application Client ID";
     String clientSecret = "CbItac10UstHhzEN2ez9UQ6b0CrPwwCJ91xopn1P";     // Application Client Secret";
     try {
         String text = URLEncoder.encode(responsetext, "UTF-8"); // 13자
         
         // clova voice 선택
         String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
         //String apiURL = "https://naveropenapi.apigw.ntruss.com/voice-premium/v1/tts"; 올바른 서비스 아님! 230error
         //String apiURL = "https://naveropenapi.apigw.ntruss.com/voice/v1/tts"; 올바른 서비스 아님! 230error
         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
         // post request
         String postParams = "speaker="+speaker+"&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(postParams);
         wr.flush();
         wr.close();
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if(responseCode==200) { // 정상 호출
             InputStream is = con.getInputStream();
             int read = 0;
             byte[] bytes = new byte[1024];
             // 랜덤한 이름으로 mp3 파일 생성
             String tempname = Long.valueOf(new Date().getTime()).toString();
             File f = new File("C:\\kdigital\\images\\"+tempname + ".mp3");
             returnfile = tempname +".mp3";
             f.createNewFile();
             OutputStream outputStream = new FileOutputStream(f);
             while ((read =is.read(bytes)) != -1) {
                 outputStream.write(bytes, 0, read);
             }
             is.close();
         } else {  // 오류 발생
             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = br.readLine()) != null) {
                 response.append(inputLine);
             }
             br.close();
             System.out.println(response.toString());
         }
     } catch (Exception e) {
         System.out.println(e);
     }
     return returnfile;
 }
}
