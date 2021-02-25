package com.SilentEight.algorithm.service.impl;

import com.SilentEight.algorithm.models.request.AlgorithmRequest;
import com.SilentEight.algorithm.models.response.Tokens;
import com.SilentEight.algorithm.service.AlgorithmService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class AlgorithmServiceImpl implements AlgorithmService{

    @Override
    public Tokens getTokens() {
        String femaleTokensPaths = "src/main/java/com/SilentEight/algorithm/tokens/femaleTokens.txt";
        String maleTokensPaths = "src/main/java/com/SilentEight/algorithm/tokens/maleTokens.txt";

        ArrayList<String> females = getTokensFromFile(femaleTokensPaths);
        ArrayList<String> males = getTokensFromFile(maleTokensPaths);

        Tokens tokens = new Tokens();
        tokens.setFemaleTokens(females);
        tokens.setMaleTokens(males);
        return tokens;
    }

    protected ArrayList<String> getTokensFromFile(String source){
        ArrayList<String> tokensList = new ArrayList<>();
        try {
            BufferedReader tokensReader = new BufferedReader(new FileReader(source));
            String token;
            while ((token = tokensReader.readLine()) != null) {
                tokensList.add(token);
            }
            tokensReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokensList;
    }

    @Override
    public String checkGender(AlgorithmRequest algorithmRequest) {
        String[] names = algorithmRequest.getNames().split("\\W+");
        String femaleTokensPaths = "src/main/java/com/SilentEight/algorithm/tokens/femaleTokens.txt";
        String maleTokensPaths = "src/main/java/com/SilentEight/algorithm/tokens/maleTokens.txt";

        String returnValue;
        if(algorithmRequest.isCheckOnlyFirstName()) returnValue = checkOnlyFirstName(names,femaleTokensPaths,maleTokensPaths);
        else returnValue = checkAllNames(names,femaleTokensPaths,maleTokensPaths);

        return returnValue;
    }

    protected String checkOnlyFirstName(String[] names, String femaleTokensPath, String maleTokensPath) {
        String nameToCheck = names[0];
        if(checkIfNameInsideTokens(femaleTokensPath, nameToCheck)) return "FEMALE";
        else if (checkIfNameInsideTokens(maleTokensPath , nameToCheck)) return "MALE";
        return "INCONCLUSIVE";
    }


    protected String checkAllNames(String[] names, String femaleTokensPath, String maleTokensPath) {
        int maleTokens = 0;
        int femaleTokens = 0;
        for(String name : names){
            if(checkIfNameInsideTokens(femaleTokensPath, name)) femaleTokens++;
            else if (checkIfNameInsideTokens(maleTokensPath , name)) maleTokens++;
        }
        if(femaleTokens>maleTokens) return "FEMALE";
        if(femaleTokens<maleTokens) return "MALE";
        return "INCONCLUSIVE";
    }

    protected Boolean checkIfNameInsideTokens(String source, String name){
        try {
            BufferedReader tokensReader = new BufferedReader(new FileReader(source));
            String token;
            name = name.toLowerCase(Locale.ROOT);
            while ((token = tokensReader.readLine()) != null) {
                token = token.toLowerCase(Locale.ROOT);
                if(token.equals(name)) return true;
            }
            tokensReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
