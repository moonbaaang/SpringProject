package ajax;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


// Ajax 요청 처리 전용 컨트롤러로 사용
@RestController //@ResponseBody 붙이지 않아도 된다.
public class AllAjaxController {
	@RequestMapping("/a")
	public BoardDTO a() {
		return new BoardDTO();
	}
	
	@RequestMapping("/b")
	public String b() {
		return "b";
	}

	@RequestMapping("/c")
	public Integer c() {
		return 0;
	}
}
