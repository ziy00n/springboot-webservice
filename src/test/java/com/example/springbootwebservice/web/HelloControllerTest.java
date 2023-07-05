package com.example.springbootwebservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is; // 수동으로 작성해줌.
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class) // (1)
@WebMvcTest(controllers = HelloController.class) // (2)
public class HelloControllerTest {
    @Autowired // (3)
    private MockMvc mvc; // (4)

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc 통해 /hello 주소로 HTTP GET 요청 (아래 검증기능 이어서 선언 가능)
                .andExpect(status().isOk()) // mvc.perform 결과 검증, HTTP Header Status 검증
                .andExpect(content().string(hello)); // Controller => "hello" 리턴 값 맞는지 검증
    }

    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // (1)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // (2)
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}