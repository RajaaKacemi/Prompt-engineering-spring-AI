package com.enset.promptengineeringspringai.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentController {
    private ChatClient chatClient; // ChatClient est une interface qui nous a permet de communiquer qvec n'import quelle LLM.

    // Injection des dependeces avec le constructeur
    public AIAgentController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String askLLM(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
