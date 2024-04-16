package com.edwinsoto.tipoftheday.Controller;

import com.edwinsoto.tipoftheday.tip.Tip;
import com.edwinsoto.tipoftheday.tip.TipRepository;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@RestController
public class OllamaController {

    private final OllamaChatClient chatClient;
    private final TipRepository tipRepository;

    @Autowired
    public OllamaController(OllamaChatClient chatClient, TipRepository tipRepository) {
        this.chatClient = chatClient;
        this.tipRepository = tipRepository;
    }

    @GetMapping("/ollama/java")
    public Tip generateJavaTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Java posed as a question then answer") String message) {
        return generateTip("java-tip", message);
    }

    @GetMapping("/ollama/python")
    public Tip generatePythonTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Python3 posed as a question then answer") String message) {
        return generateTip("python-tip", message);

    }

    @GetMapping("/ollama/design")
    public Tip generateDesignTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on software design posed as a question then answer") String message) {
        return generateTip("design-tip", message);

    }


    private Tip generateTip(String endpoint, String message) {
        String response = chatClient.call(message);
        Tip tip = parseTip(response);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDate = LocalDateTime.now().format(formatter);
        tip.setDate(formattedDate);

        tip.setEndpoint(endpoint);
        System.out.println(tip);
        tipRepository.save(tip);
        return tip;
    }

    private Tip parseTip(String response) {
        Tip tip = new Tip();

        int questionIndex = response.indexOf("Question:");

        if (questionIndex != -1) {
            String questionPart = response.substring(questionIndex + "Question:".length()).trim();

            int answerIndex = questionPart.indexOf("Answer:");

            if (answerIndex != -1) {
                String question = questionPart.substring(0, answerIndex).trim();
                String answer = questionPart.substring(answerIndex + "Answer:".length()).trim();

                tip.setQuestion(question);
                tip.setAnswer(answer);
            }
        }
        return tip;
    }

}
