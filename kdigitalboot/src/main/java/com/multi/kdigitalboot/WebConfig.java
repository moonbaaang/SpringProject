package com.multi.kdigitalboot;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //현재 클래스 설정 모든 결과 xml파일 < bean으로 해석
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**") //http://127.0.0.1:9090/....
                .addResourceLocations("file:///c:/upload/"); // 이 위치에 있는 것들을 /upload로 매핑했음
        registry.addResourceHandler("/faceimages/**") //http://127.0.0.1:9090/....
        .addResourceLocations("file:///c:/kdigital/images/"); // 실제경로
        registry.addResourceHandler("/projecttest/**") //http://127.0.0.1:9090/....
        .addResourceLocations("file:///c:/upload/"); // 실제경로
    }
}