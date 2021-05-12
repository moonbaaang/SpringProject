package memberanno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class MemberDAO {
	@Autowired //값이 2개 인데? 아래와같이 값 설정
	@Qualifier("vo2")
	MemberVO vo;
	
	public void setMemberVO(MemberVO vo) {
		this.vo = vo;
	}
	
	public boolean selectMember() {
		if(vo.getId().equals("spring") && vo.getPw().equals("work")){
			return true;
		}
		else {
			return false;
		}
	}
	public void insertMember() {
		System.out.println(vo.getId()+" 회원님 정상 가입되셨습니다.");
	}
}
