package com.multi.myboot01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// annotation이 사용되려면 기존에는 <context:component-scan base-packages="@Controller...클래스 패키지명">
// boot에서는 servlet-context.xml 이 사라졌기에 Myboot01Application.java에
@Controller
public class HelloController {
	
	@RequestMapping("/")
//	@ResponseBody
	public String test() {
		return "hello";
	}
}
/* spring mvc project view >> servlet-context.xml 파일 설정 /web-inf/view/*.jsp
	http://ip:port/컨테스트명/매핑url >> mvc에서 실행 url
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	
	spring boot >> http://ip:port/매핑url  >> context 없음
	1> src/main/webapp/web-inf/views 폴더 생성
	2> hello.jsp 생성
	3> application.properties 설정
	4> pox.xml - jsp사용 라이브러리 다운로드 설정/tomcat restart 라이브러리 다운로드 설정

	<!-- for jsp -->
		<dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
        <scope>provided</scope>
    </dependency>
		<!-- for jsp jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
    </dependency>

*/