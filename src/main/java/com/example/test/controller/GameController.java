package com.example.test.controller;


import com.example.test.dto.CreateGameDto;
import com.example.test.dto.EndGameDto;
import com.example.test.service.PlayGame;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class GameController {

    private PlayGame playGame;

    @PostMapping
    public EndGameDto startGame(@RequestBody final CreateGameDto words) {
        return playGame.result(words);
    }
}
