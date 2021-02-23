package com.SilentEight.algorithm.service.impl;

import com.SilentEight.algorithm.models.response.Tokens;
import com.SilentEight.algorithm.service.AlgorithmService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class AlgorithmServiceImpl implements AlgorithmService{
    @Override
    public Tokens getTokens() {
        Tokens tokens = new Tokens();

        try{
            ArrayList<String> females = new ArrayList<>();
            BufferedReader femaleTokensReader = new BufferedReader(new FileReader("src/main/java/com/SilentEight/algorithm/tokens/femaleTokens.txt"));
            String femaleToken;
            while ((femaleToken = femaleTokensReader.readLine()) != null) {
                females.add(femaleToken);
            }
            femaleTokensReader.close();
            tokens.setFemaleTokens(females);

            ArrayList<String> males = new ArrayList<>();
            BufferedReader maleTokensReader = new BufferedReader(new FileReader("src/main/java/com/SilentEight/algorithm/tokens/maleTokens.txt"));
            String maleToken;
            while ((maleToken = maleTokensReader.readLine()) != null) {
                males.add(maleToken);
            }
            maleTokensReader.close();
            tokens.setMaleTokens(males);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokens;
    }

    public static void main(String[] args) {
    AlgorithmServiceImpl algorithmService = new AlgorithmServiceImpl();
    algorithmService.getTokens();
    }
}
