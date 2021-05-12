package multi.campus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CRUDController {
	//crud - create read update delete - 생성 조회 수정 삭제 기본
	// 1개 여러개 요청 메소드들 다수개 정의
	// 게시물 생성 조회 삭제 수정 > 하나의 컨트롤러로 설정
	@RequestMapping("/crud/start") //각각 메서드 위에는 RequestMapping
	public void start() {
		//모델 없음 
		// 뷰 이름 자동 결정 - web-inf/views/crud/start.jsp 실행		
	}
	
	@RequestMapping("/crud/list")
	public ModelAndView list() {
		String [] title = {"게시물 1", "게시물 2", "게시물 3"};
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", title);
		mv.setViewName("/crud/list");
		return mv;
		
	}
	
	@RequestMapping(value="/crud/add", method=RequestMethod.GET)
	public String addform() {
		return "/crud/addform";
	}
	
	@RequestMapping(value="/crud/add", method=RequestMethod.POST)
	public ModelAndView addresult(String title, String contents, String writer) {
		ModelAndView mv = new ModelAndView();
		// 한글이 깨져서 전달오게됨
		mv.addObject("board", title+":"+contents+":"+writer);
		mv.addObject("status", "게시물 1개 저장완료");
		//mv.setViewName("crud/add"); / 뷰이름 지정 안할 시 자동으로 value값 받아옴
		return mv;
	}

	
	@RequestMapping(value="/crud/update", method=RequestMethod.GET)
	public ModelAndView updateform() {
		// 제목 내용 작성자를 미리 폼에 보여주고 수정해야함
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "선택한게시물제목");
		mv.addObject("contents", "선택한게시물내용");
		mv.addObject("writer", "작성자");
		mv.setViewName("/crud/updateform");
		return mv;
	}
	
	@RequestMapping(value="/crud/update", method=RequestMethod.POST)
	public ModelAndView updateresult(String title, String contents, String writer) {
		ModelAndView mv = new ModelAndView();
		// 한글이 깨져서 전달오게됨
		mv.addObject("board", title+":"+contents+":"+writer);
		mv.addObject("status", "게시물 1개 수정완료");
		//mv.setViewName("crud/update"); / 뷰이름 지정 안할 시 자동으로 value값 받아옴
		return mv;
	}
	
	@RequestMapping("/crud/delete")
	public ModelAndView delete
	(@RequestParam(value="seq", required=false, defaultValue="1") int seq) {
		ModelAndView mv = new ModelAndView();
		System.out.println(seq + "번의 글 삭제");
		mv.addObject("seq", seq);
		mv.setViewName("redirect:/crud/list"); //
		//return "/crud/list"; //view이름 이동(model 없다)
		return mv;//url 매핑 메소드 이동(model 있다)
	}

}

