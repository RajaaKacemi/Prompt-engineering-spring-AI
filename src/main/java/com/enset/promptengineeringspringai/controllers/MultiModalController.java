package com.enset.promptengineeringspringai.controllers;

import com.enset.promptengineeringspringai.outputs.CinModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping(value = "/askImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String askImage(String question, @RequestParam(name = "file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return chatClient.prompt()
                .system("Repondre a la question de l'utilisateur a propose de l'image fournie")
                .user(
                        u->
                                u.text(question)
                                        .media(MediaType.IMAGE_PNG, new ByteArrayResource(bytes))
                )
                .call()
                .content();
    };
}
