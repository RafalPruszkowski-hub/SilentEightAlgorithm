package com.SilentEight.algorithm.service;

import com.SilentEight.algorithm.models.request.AlgorithmRequest;
import com.SilentEight.algorithm.models.response.Tokens;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AlgorithmService {
    Tokens getTokens();
    String checkGender(AlgorithmRequest algorithmRequest);
}
