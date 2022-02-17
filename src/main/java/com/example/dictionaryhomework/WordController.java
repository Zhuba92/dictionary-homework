package com.example.dictionaryhomework;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class WordController {
    private WordDefinitionService wordDefinitionService;
    private Word[] words;

    @PostConstruct
    private void initWordsData(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            words = mapper.readValue(Paths.get("words.json").toFile(), Word[].class);
        } catch(IOException e) {
            e.printStackTrace();
            words = new Word[0];
        }
    }

    @Autowired
    public WordController(WordDefinitionService wds){
        this.wordDefinitionService = wds;
    }

    @RequestMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("words", words);
        return "index";
    }

    @RequestMapping("/display-word/{word}")
    public String wordPage (@PathVariable String word, Model model){
        model.addAttribute("wds", wordDefinitionService.getWord(word));
        return "display-word";
    }

    @RequestMapping("/search")
    public String showSearchPage(Model model){
        model.addAttribute("wds", wordDefinitionService.getDefinition());
        model.addAttribute("words", words);
        Word word = new Word();
        word.setName("cat");
        word.setDefinition("");
        model.addAttribute("word", word);
        return "search";
    }

    @RequestMapping("/get-word")
    public String searchWordPage (@RequestParam(name = "search") String word){
        return "redirect:/display-word/" + word;
    }
}
