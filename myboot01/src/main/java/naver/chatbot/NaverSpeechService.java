package naver.chatbot;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import naver.cloud.NaverService;

@Service("chatbotspeechService")
// NaverSpeechService naverSpeechService = new NaverSpeechService(); >> @Service 이렇게 자동으로 만들어짐
// NaverSpeechService chatbotspeechService = new NaverSpeechService(); 
public class NaverSpeechService implements NaverService{
	// implements NaverService 를 안했다??
	public String test(String image) {
		return test(image, "Kor");
	} // speechinput.jsp에서 lang정보가 선택되지 않았을 때 기본 호출
    
	
	public String test(String mp3file,String language) {
    	StringBuffer response = new StringBuffer();
    	String clientId = "nq848nfz2h";             // Application Client ID";
        String clientSecret = "CbItac10UstHhzEN2ez9UQ6b0CrPwwCJ91xopn1P";     // Application Client Secret";

        try {
           // String imgFile = "C:\\kdigital\\images\\news1.mp3";
            File voiceFile = new File("C:\\kdigital\\images\\"+mp3file);

           // String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096]; //4kb, 60sec 제한
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            // ~ 까지 서버로 요청
            
            // 서버로부터 응답 받음
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            
            System.out.println(responseCode); //테스트겸 출력
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if(br != null) {
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return response.toString();
    }
}
