package memberservice;

public class MemberServiceImpl implements MemberService {
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
