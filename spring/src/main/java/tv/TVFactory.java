package tv;

public class TVFactory {
	public static TV getTV(String name) {
		if(name.equals("엘지티비")) {
			return  new LGTV();		
		} else if(name.equals("삼성티비")) {
			return new SamsungTV(); // 자바소스 수정, 컴파일 과정을 없애보자
		}
		return new SamsungTV();
	}
}
