package com.example.dictionaryhomework;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Word;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WordDefinitionService implements WordService {
    private List<Word> wordList;

    @Override
    public List<Word> getDefinition() {
        return wordList;
    }

    @PostConstruct
    public void initWords(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Word[] wordArray = mapper.readValue(Paths.get("words.json").toFile(), Word[].class);
            wordList = Arrays.asList(wordArray);
        } catch(IOException e) {
            e.printStackTrace();
            wordList = new ArrayList<>(0);
        }
    }

    public Word getWord(String name) {
        int index = 0;
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).getName().equals(name)){
                index = i;
            }
        }
        return wordList.get(index);
    }
}
