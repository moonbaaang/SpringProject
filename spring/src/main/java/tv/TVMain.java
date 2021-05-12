package tv;

import org.xml.sax.SAXNotRecognizedException;

public class TVMain {

	// tv 객체를 변경하니 같이 변경 코드가 따라오는 경우 - coupling이 높다
	// 커플링이 높은 코드가 필요할 수도 / 낮은 코드가 필요할 수도 있다.
	// 인터페이스 - 메소드 선언 - 상속 하위클래스들 표준화 메소드 오버라이딩, 여러 하위 클래스 타입 호환
	// 엘지티비 / 삼성티비
	public static void main(String[] args) {
		TV tv = TVFactory.getTV(args[0]); //TVFactory에 의존 - 의존성 코드
		
		tv.powerOn();
		tv.soundUp();
		tv.soundDown();
		tv.powerOff();
		

	}

}
