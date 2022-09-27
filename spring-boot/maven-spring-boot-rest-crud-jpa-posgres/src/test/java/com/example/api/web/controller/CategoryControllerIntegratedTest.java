package com.example.api.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//TODO Correguir test
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIntegratedTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        //Default
    }

    @Test
    void authToken_ok() throws Exception {
//        AuthenticationRequest request = objectMapper
//                .readValue(new ClassPathResource("rest/JwtAuthenticationController/token-request.json")
//                        .getFile(), AuthenticationRequest.class);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/auth/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers
//                        .status()
//                        .isOk());
    }

    @Test
    void getAllCategory_ok() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/cart/category/all")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .header("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuIiwiaWF0IjoxNjU3MzE3MTQ3LCJleHAiOjE2NTczMTcyMDd9.Koo7XHMtwbTbXQ8qWj4DunalrrA8WloLgysgTtF6KGA"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers
//                        .status()
//                        .isOk());
    }
}
