package com.edwinsoto.tipoftheday.rag;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/ollama")
public class RagController {

    private final OllamaChatClient chatClient;
    private final RagRepository ragRepository;

    @Autowired
    public RagController(OllamaChatClient chatClient, RagRepository ragRepository) {
        this.chatClient = chatClient;
        this.ragRepository = ragRepository;
    }

    @GetMapping("/java")
    public RagTip generateJavaTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Java posed as a question then answer") String message) {
        RagTip tip = ragRepository.getRagTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "java");
        return Objects.requireNonNullElseGet(tip, () -> generateTip("Java", message));
    }

    @GetMapping("/python")
    public RagTip generatePythonTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Python3 posed as a question then answer") String message) {
        RagTip tip = ragRepository.getRagTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "python");
        return Objects.requireNonNullElseGet(tip, () -> generateTip("Python", message));
    }

    @GetMapping("/design")
    public RagTip generateDesignTip(@RequestParam(value = "message", defaultValue = "Give me a developer tip on software design posed as a question then answer") String message) {
        RagTip tip = ragRepository.getRagTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "design");
        return Objects.requireNonNullElseGet(tip, () -> generateTip("Design", message));
    }

    private RagTip generateTip(String tipType, String message) {
        String response = chatClient.call(message);
        response = replaceMDTagsWithHtmlTags(response);
        Map<String, String> tipResponse = parseResponse(response);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDateTime.now().format(formatter);

        RagTip tip = new RagTip(null, tipType, formattedDate, tipResponse.get("question"), tipResponse.get("answer"));

        System.out.println(tip);
        ragRepository.save(tip);
        return tip;
    }

    private Map<String,String> parseResponse(String response) {
        Map<String,String> map = new HashMap<>();
        int questionIndex = response.indexOf("Question:");

        if (questionIndex != -1) {
            String questionPart = response.substring(questionIndex + "Question:".length()).trim();

            int answerIndex = questionPart.indexOf("Answer:");

            if (answerIndex != -1) {
                String question = questionPart.substring(0, answerIndex).trim();
                String answer = questionPart.substring(answerIndex + "Answer:".length()).trim();

                question = cleanQuestion(question);
                answer = cleanAnswer(answer);

                map.put("question", question);
                map.put("answer", answer);
                System.out.println(map.get("answer"));
            }
        }
        return map;
    }

    private String replaceMDTagsWithHtmlTags(String response) {

        return response.replaceAll("\\n","<br>")
                .replaceFirst("\\*\\*", "<strong>")
                .replaceAll("\\*\\*", "</strong>")
                .replaceFirst("##", "<h2>")
                .replaceAll("##", "</h2>")
                .replaceAll("\\*", "-");
    }

    private String cleanQuestion(String question) {
        return question.replaceAll("<br>", "");
    }

    private String cleanAnswer(String answer) {
        return answer.replaceFirst("<br>", "");
    }


}
