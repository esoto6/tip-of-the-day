package com.edwinsoto.tipoftheday;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {

    private final OllamaChatClient chatClient;

    @Autowired
    public OllamaController(OllamaChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/java-tip")
    public String generateJavaTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Java") String message) {
        String response = chatClient.call(message);
        System.out.println(response);
        return chatClient.call(message);
    }

    @GetMapping("/python-tip")
    public String generatePythonTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Python3") String message) {
        return chatClient.call(message);
    }

    @GetMapping("/design-tip")
    public String generateDesignTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on software design") String message) {
        return chatClient.call(message);
    }


}
