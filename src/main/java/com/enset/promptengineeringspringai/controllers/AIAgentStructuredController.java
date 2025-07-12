package com.enset.promptengineeringspringai.controllers;


import com.enset.promptengineeringspringai.outputs.Movie;
import com.enset.promptengineeringspringai.outputs.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentStructuredController {
    private ChatClient chatClient;

    public AIAgentStructuredController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/askAgent")
    public MovieList askAgent(String query) {
        String systemMessages="""
                Vous etes un specialiste dans le domaine du cinema
                Repondre a la question du utilisateur a ce propos
                """;

        return chatClient.prompt()
                .system(systemMessages)
                .user(query)
                .call()
                .entity(MovieList.class);
    }
}
