package com.codingrecipe.board.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/api/**")
               .allowedOrigins("https://localhost:8081") // Vue.js 개발 서버 허용 / 
           /* 프론트엔드에서 boardApi.js에는 8080, 
           백엔드 CorsConfig.java에는 allowedOrigins에 프론트엔드에서 실행되는 로컬 포트번호를 입력해야한다. 
           또한 yml에 포트번호는 8080이다.*/
               .allowedMethods("GET", "POST", "PUT", "DELETE")
               .allowedHeaders("*")
               .allowCredentials(true);
    }
}
