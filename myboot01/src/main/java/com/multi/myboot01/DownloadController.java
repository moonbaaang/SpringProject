package com.multi.myboot01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {

	@RequestMapping("/filedownload")
	public ModelAndView downloadform(){
		ModelAndView mv = new ModelAndView();
		File path = new File("c:/upload");
		String [] filelist = path.list(); //파일명을 배열 리턴
		mv.addObject("details", filelist);
		mv.setViewName("upload/downloadform");
		return mv;
	}
	
	@RequestMapping("/downloadresult")
	public void downloadresult(String file, HttpServletResponse response) throws IOException{ // 다운로드 할 파일 명
		// 파일 다운로드 후 이동할 뷰는 필요 없다 > void type
		File f = new File("c:/upload", file); //upload의 파일명
		
		// 파일 다운로드를 응답하겠다고 선언. 파일명, 길이 포함
		response.setContentType("application/download");
		response.setContentLength((int)f.length()); //다운받아야할 파일의 길이 
		response.setHeader("Content-Disposition", "attachment;filename=\""+file+"\"");
		
		//파일명에 해당하는 파일을 읽어 클라이언트에서 복사 출력
		OutputStream out = response.getOutputStream(); //클라이언트에게 출력
		FileInputStream fin = new FileInputStream(f); //파일을 읽어서 브라우저에 출력
		FileCopyUtils.copy(fin, out); //복사
		fin.close();
		out.close();
	}
}
