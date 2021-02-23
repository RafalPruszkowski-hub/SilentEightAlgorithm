package com.SilentEight.algorithm.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tokens {
    private ArrayList<String> femaleTokens;
    private ArrayList<String> maleTokens;
}
