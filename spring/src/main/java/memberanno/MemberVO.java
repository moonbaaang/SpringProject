package memberanno;

import org.springframework.stereotype.Component;

@Component("vo") //vo 설정 하지 않을 시 MemberVO memberVO = new MemberVO()로 자동 만듬
public class MemberVO {
	String id, pw;
	
	MemberVO(){}
	MemberVO(String id, String pw){
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
