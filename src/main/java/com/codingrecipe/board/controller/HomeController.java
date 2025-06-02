package com.codingrecipe.board.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor // 역시 Lombok이 제공하는 어노테이션
public class HomeController {
    // 컨트롤러는 서비스, 서비스는 레퍼지토리 클래스를 사용해야한다.
    // 의존성 주입
    // 생성자 주입 사용

    // 시작페이지 - index.html을 화면에 띄우겠다
    @GetMapping("/") // 어노테이션
    public String index(){
        System.out.println("HomeController.index");
        return "index";
    }
}
