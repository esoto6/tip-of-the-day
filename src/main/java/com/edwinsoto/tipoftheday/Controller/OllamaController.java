package com.edwinsoto.tipoftheday.Controller;

import com.edwinsoto.tipoftheday.Model.Tip;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OllamaController {

    private final OllamaChatClient chatClient;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public OllamaController(OllamaChatClient chatClient, MongoTemplate mongoTemplate) {
        this.chatClient = chatClient;
        this.mongoTemplate = mongoTemplate;
    }



    @GetMapping("/java-tip")
    public Map<String, String> generateJavaTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Java posed as a question then answer") String message) {
        return generateTip("java-tip", message);
    }

    @GetMapping("/python-tip")
    public Map<String, String> generatePythonTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Python3 posed as a question then answer") String message) {
        return generateTip("python-tip", message);

    }

    @GetMapping("/design-tip")
    public Map<String, String> generateDesignTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on software design posed as a question then answer") String message) {
        return generateTip("design-tip", message);

    }


    private Map<String, String> generateTip(String endpoint, String message) {
        String response = chatClient.call(message);
        Map<String, String> responseMap = parseTip(response);
        responseMap.put("DateTime", LocalDateTime.now().toString());
        responseMap.put("Endpoint", endpoint);
        System.out.println(responseMap);
        SaveTip(responseMap);
        return responseMap;
    }

    private Map<String, String> parseTip(String response) {
        Map<String, String> tip = new HashMap<>();

        // Find the index of "Question:" in the response
        int questionIndex = response.indexOf("Question:");

        // If "Question:" is found
        if (questionIndex != -1) {
            // Extract the question text
            String questionPart = response.substring(questionIndex + "Question:".length()).trim();

            // Find the index of "Answer:" in the question part
            int answerIndex = questionPart.indexOf("Answer:");

            // If "Answer:" is found
            if (answerIndex != -1) {
                // Extract the question and answer text
                String question = questionPart.substring(0, answerIndex).trim();
                String answer = questionPart.substring(answerIndex + "Answer:".length()).trim();

                // Put the question and answer into the map
                tip.put("Question", question);
                tip.put("Answer", answer);
            }
        }

        return tip;
    }

    private void SaveTip(Map<String,String> tipMap){
        Tip tipDocument = new Tip();
        tipDocument.setQuestion(tipMap.get("Question"));
        tipDocument.setAnswer(tipMap.get("Answer"));
        tipDocument.setDateTime(tipMap.get("DateTime"));
        tipDocument.setEndpoint(tipMap.get("Endpoint"));
        mongoTemplate.save(tipDocument);
    }
}
