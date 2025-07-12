package com.enset.promptengineeringspringai.controllers;

import com.enset.promptengineeringspringai.outputs.CinModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiModalController {

    private ChatClient chatClient;
    @Value("classpath:/images/CIN.png")
    private Resource image;

    public MultiModalController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();

    }

    @GetMapping("/describe")
    public CinModel describeImage(){
        return chatClient.prompt()
                .system("DOnne une description de l'image fournie")
                .user(
                        u->
                                u.text("Decrire cette image")
                                        .media(MediaType.IMAGE_PNG, image)
                )
                .call()
                .entity(CinModel.class);
    };

    @GetMapping("/askImage")
    public CinModel askImage(){
        return chatClient.prompt()
                .system("Repondre a la question de l'utilisateur a propose de l'image manuscrite fournie")
                .user(
                        u->
                                u.text("")
                                        .media(MediaType.IMAGE_PNG, image)
                )
                .call()
                .entity(CinModel.class);
    };
}
