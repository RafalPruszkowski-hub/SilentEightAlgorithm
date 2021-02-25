package com.SilentEight.algorithm.service.impl;

import com.SilentEight.algorithm.exceptions.ServerSideException;
import com.SilentEight.algorithm.models.request.AlgorithmRequest;
import com.SilentEight.algorithm.models.response.Tokens;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest(AlgorithmServiceImpl.class)
public class AlgorithmServiceImplTest {
    private final String maleTokensSource = "src/test/java/com/SilentEight/algorithm/tokens/maleTokens.txt";
    private final String femaleTokensSource = "src/test/java/com/SilentEight/algorithm/tokens/femaleTokens.txt";
    private final String emptyTokensSource = "src/test/java/com/SilentEight/algorithm/tokens/emptyTokens.txt";
    private final String notExistingTokensSource = "src/test/java/com/SilentEight/algorithm/tokens/notExistingTokens.txt";
    @Autowired
    private AlgorithmServiceImpl algorithmService;

    @Test
    public void checkIfNameInsideTokens_true() {
        Boolean result = algorithmService.checkIfNameInsideTokens(maleTokensSource, "wojtek");
        assertEquals(result, true);
    }

    @Test
    public void checkIfNameInsideTokens_false() {
        Boolean result = algorithmService.checkIfNameInsideTokens(maleTokensSource, "abc");
        assertEquals(result, false);
    }

    @Test
    public void checkIfNameInsideTokens_emptyTokensList() {
        Boolean result = algorithmService.checkIfNameInsideTokens(emptyTokensSource, "wojtek");
        assertEquals(false, result);
    }

    @Test
    public void checkIfNameInsideTokens_exception() {
        assertThrows(ServerSideException.class, () -> {
            algorithmService.checkIfNameInsideTokens(notExistingTokensSource, "wojtek");
        });
    }

    @Test
    public void checkAllNames_basicInconclusive() {
        String[] names = {"Wojtek", "Agnieszka"};
        String result = algorithmService.checkAllNames(names, femaleTokensSource, maleTokensSource);
        assertEquals("INCONCLUSIVE", result);
    }

    @Test
    public void checkAllNames_basicMale() {
        String[] names = {"Wojtek", "Kuba"};
        String result = algorithmService.checkAllNames(names, femaleTokensSource, maleTokensSource);
        assertEquals("MALE", result);
    }

    @Test
    public void checkAllNames_basicFemale() {
        String[] names = {"Agnieszka", "Ada"};
        String result = algorithmService.checkAllNames(names, femaleTokensSource, maleTokensSource);
        assertEquals("FEMALE", result);
    }

    @Test
    public void checkAllNames_complexFemale() {
        String[] names = {"Agnieszka", "Ada", "Kuba"};
        String result = algorithmService.checkAllNames(names, femaleTokensSource, maleTokensSource);
        assertEquals("FEMALE", result);
    }

    @Test
    public void checkAllNames_complexMale() {
        String[] names = {"Agnieszka", "Kuba", "Wojtek"};
        String result = algorithmService.checkAllNames(names, femaleTokensSource, maleTokensSource);
        assertEquals("MALE", result);
    }

    @Test
    public void checkAllNames_TokensThatAreNotInList() {
        String[] names = {"abcd"};
        String result = algorithmService.checkAllNames(names, femaleTokensSource, maleTokensSource);
        assertEquals("INCONCLUSIVE", result);
    }

    @Test
    public void checkOnlyFirstName_basicInconclusive() {
        String[] names = {"asd", "Kuba", "Wojtek"};
        String result = algorithmService.checkOnlyFirstName(names, femaleTokensSource, maleTokensSource);
        assertEquals("INCONCLUSIVE", result);
    }

    @Test
    public void checkOnlyFirstName_basicFemale() {
        String[] names = {"Agnieszka", "Kuba", "Wojtek"};
        String result = algorithmService.checkOnlyFirstName(names, femaleTokensSource, maleTokensSource);
        assertEquals("FEMALE", result);
    }

    @Test
    public void checkOnlyFirstName_basicMale() {
        String[] names = {"Kuba", "Agnieszka", "Ania"};
        String result = algorithmService.checkOnlyFirstName(names, femaleTokensSource, maleTokensSource);
        assertEquals("MALE", result);
    }

    @Test
    public void checkGender_basicOnlyFirstName() {
        AlgorithmRequest algorithmRequest = new AlgorithmRequest();
        algorithmRequest.setCheckOnlyFirstName(true);
        algorithmRequest.setNames("Wojtek Agnieszka Ania");
        String result = algorithmService.checkGender(algorithmRequest);
        assertEquals("MALE", result);
    }

    @Test
    public void checkGender_basicAllNames() {
        AlgorithmRequest algorithmRequest = new AlgorithmRequest();
        algorithmRequest.setCheckOnlyFirstName(false);
        algorithmRequest.setNames("Wojtek Agnieszka Ania");
        String result = algorithmService.checkGender(algorithmRequest);
        assertEquals("FEMALE", result);
    }

    @Test
    public void getTokensFromFile_basic() {
        ArrayList<String> result = algorithmService.getTokensFromFile(maleTokensSource);
        assertEquals(3, result.size());
        assertEquals("Wojtek", result.get(0));
        assertEquals("Marcin", result.get(1));
        assertEquals("Kuba", result.get(2));
    }

    @Test
    public void getTokensFromFile_exception() {
        assertThrows(ServerSideException.class, () -> {
            algorithmService.getTokensFromFile(notExistingTokensSource);
        });
    }

    @Test
    public void getTokens(){
        Tokens tokens = algorithmService.getTokens();
        ArrayList<String> femaleTokens = tokens.getFemaleTokens();
        ArrayList<String> maleTokens = tokens.getMaleTokens();
        assertEquals(10, femaleTokens.size());
        assertEquals(10, femaleTokens.size());
    }
}