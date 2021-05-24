package com.multi.myboot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
// 부트 시작 클래스

import naver.chatbot.NaverChatbotController;
import naver.cloud.NaverController;
import spring_mybatis.EmpController;
import spring_mybatis.EmpDAO;

// @ComponentScan(basePackageClasses="패키지명")
// @ComponentScan (생락가능)
// 이를 어노테이션으로 사용할 수 있음
// servlet-context.xml <context:component-scan base-packages="@Controller...클래스 패키지명">
// 현재 포함된 패키지가 같으면 생략이 가능, 다른 패키지는 등록해야 사용 가능

@SpringBootApplication
@ComponentScan
@ComponentScan(basePackageClasses = EmpController.class)
@ComponentScan(basePackageClasses = NaverController.class)
@ComponentScan(basePackageClasses = NaverChatbotController.class)
// EmpController가 있는 spring_mybatis 내부에서 annotation 사용 가능
@MapperScan(basePackageClasses = EmpDAO.class)
public class Myboot01Application {

	public static void main(String[] args) {
		SpringApplication.run(Myboot01Application.class, args);
	}
	
}
