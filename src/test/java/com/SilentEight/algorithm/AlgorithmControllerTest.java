package com.SilentEight.algorithm;

import com.SilentEight.algorithm.controller.AlgorithmController;
import com.SilentEight.algorithm.models.response.Tokens;
import com.SilentEight.algorithm.service.AlgorithmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlgorithmController.class)
public class AlgorithmControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlgorithmService algorithmService;

    @Test
    public void getTokens_basic() throws Exception {
        ArrayList<String> femaleTokens = new ArrayList<>();
        femaleTokens.add("a");
        femaleTokens.add("b");
        femaleTokens.add("c");

        ArrayList<String> maleTokens = new ArrayList<>();
        maleTokens.add("d");
        maleTokens.add("e");
        maleTokens.add("f");
        Tokens fromService = new Tokens(femaleTokens,maleTokens);
        when(algorithmService.getTokens()).thenReturn(fromService);

        RequestBuilder request = MockMvcRequestBuilders.get("/getTokens");
        mockMvc.perform(request).
                andExpect(status().isOk()).
                andExpect(content().json(
                        "{femaleTokens:[a,b,c],maleTokens:[d,e,f]}"
                        ));
    }
}
