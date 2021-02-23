package com.SilentEight.algorithm.controller;

import com.SilentEight.algorithm.models.response.Tokens;
import com.SilentEight.algorithm.service.AlgorithmService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping
public class AlgorithmController {
    private AlgorithmService algorithmService;

    @GetMapping(path ="/getTokens",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tokens getTokens(){
        return algorithmService.getTokens();
    }

    @GetMapping(path = "/algorithm",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String algorithm(){
        return "a";
    }
}
