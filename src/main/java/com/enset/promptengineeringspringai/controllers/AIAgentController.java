package com.enset.promptengineeringspringai.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIAgentController {
    private ChatClient chatClient; // ChatClient est une interface qui nous a permet de communiquer qvec n'import quelle LLM.

    // Injection des dependeces avec le constructeur
    public AIAgentController(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/chat")
    public String askLLM(String query){
        List<Message> exemples = List.of(
                new UserMessage("6+4"),
                new AssistantMessage("Le resultat est : 10")
        );
 
        return chatClient.prompt()
                .system("Always response with Uppercase")
                .messages(exemples)
                .user(query)
                .call()
                .content();
    }
}
