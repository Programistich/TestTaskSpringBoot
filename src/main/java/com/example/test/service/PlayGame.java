package com.example.test.service;

import com.example.test.dto.CreateGameDto;
import com.example.test.dto.EndGameDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayGame {

    public EndGameDto result(CreateGameDto game) {

        List<String> worldsResult = new ArrayList<>();
        List<String> worlds = game.getWords();

        // Init first world
        if(worlds.get(0).isEmpty()) return new EndGameDto(worldsResult);
        worldsResult.add(worlds.get(0));
        // Init another world
        for(int index = 1; index < worlds.size(); index++){
            if(correctWords(worlds.get(index-1), worlds.get(index))) worldsResult.add(worlds.get(index));
            else break;
        }
        return new EndGameDto(worldsResult);
    }

    // Checkable two world
    public boolean correctWords(String firstWord, String secondWord){
        if(secondWord.equals("")) return false;
        return lastSymbol(firstWord) == firstSymbol(secondWord);
    }

    public char lastSymbol(String world){
        return world.charAt(world.length() - 1);
    }

    public char firstSymbol(String world){
        return world.charAt(0);
    }
}
