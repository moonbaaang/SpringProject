package ajax;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginAjaxController {
	@RequestMapping(value="/ajax/login", method=RequestMethod.GET)
	public String loginform() { // 폼 출력 http 요청	
		return "/ajax/loginajax"; //뷰 이름
	}
	
	@RequestMapping(value="/ajax/login", method=RequestMethod.POST, 
			produces = {"applicaion/json;charset=utf-8"})
	@ResponseBody //리턴하는 string 은 뷰가 아닌 ajax 
	public String loginresult(String id, String pw) { // 폼 입력 데이터 전송 ajax요청
		//data: {'id':$("#id").val(), 'pw':$('#pw').val()} ,
		//json을 만드는 방법 {\"변수명\":\"값\", \"변수명1\":\"값1\", ...} 
		System.out.println(id+":"+pw);
		String result = null;
		
		if(id.equals("spring")&&pw.equals("spring")) {
			result = "{\"process\":\"정상로그인\", \"role\":\"admin\"}";
		}
		else {
			result = "{\"process\":\"비정상로그인\", \"role\":\"user\"}";
		}
		return result; // json문자열 형태 , 뷰 이름을 리턴하지 않기에 어노테이션을 하나 더 추가한다.
	}// loginresult end
	
/*	@RequestMapping("/ajax/board")
	@ResponseBody //모든 객체/메소드를 리턴 가능하게함, 자동으로 json으로 변경해줌
	public BoardDTO getBoardDTO(int seq){
		BoardDTO dto = new BoardDTO();
		dto.setSeq(seq);
		dto.setTitle("게시물 제목");
		dto.setContents("게시물 내용");
		dto.setWriter("작성자");
		dto.setViewcount(100);
		return dto;
	}
*/	
	// ajax/board/1 으로 설정하고싶다면? 
	@RequestMapping("/ajax/board/{seq}")
	@ResponseBody //모든 객체/메소드를 리턴 가능하게함, 자동으로 json으로 변경해줌
	public BoardDTO getBoardDTO(@PathVariable("seq") int seq){
		BoardDTO dto = new BoardDTO();
		dto.setSeq(seq);
		dto.setTitle("게시물 제목");
		dto.setContents("게시물 내용");
		dto.setWriter("작성자");
		dto.setViewcount(100);
		return dto;
	}
	
	@RequestMapping("/ajax/boardlist")
	@ResponseBody //모든 객체/메소드를 리턴 가능하게함, 자동으로 json으로 변경해줌
	public ArrayList<BoardDTO> getBoardDTO(){
		BoardDTO dto = new BoardDTO();
		dto.setSeq(1);
		dto.setTitle("게시물 제목");
		dto.setContents("게시물 내용");
		dto.setWriter("작성자");
		dto.setViewcount(100);
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		list.add(dto);
		list.add(dto);
		list.add(dto);
		list.add(dto);
		list.add(dto);
		
		return list;
		
		//rest > 한 페이지에 데이터가 연결된 것을 보여달라
	}
	

}
