package memberanno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class MemberServiceImpl implements MemberService {
	
	@Autowired //vo와 연결
	MemberDAO dao;
	
	public void setMemberDAO(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void register() {
		//ex) MemberDAO SELECTMEMBER() - true 이면 이미 존재하는 아이디
		boolean result = dao.selectMember();
		if(!result) {
			dao.insertMember();
		}
	}

	@Override
	public void login() {
		boolean result = dao.selectMember();
		if(result) {
			System.out.println("정상 로그인 사용자");
		}

	}

}
