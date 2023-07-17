package com.example.springbootwebservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 글 등록 페이지
    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }
}
