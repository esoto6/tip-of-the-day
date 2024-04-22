package com.edwinsoto.tipoftheday.service;

import com.edwinsoto.tipoftheday.record.Tip;
import com.edwinsoto.tipoftheday.repository.TipRepository;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TipService {

    private final OllamaChatClient chatClient;
    private final TipRepository repository;

    @Autowired
    private TipService(OllamaChatClient chatClient, TipRepository repository) {
        this.chatClient = chatClient;
        this.repository = repository;
    }


    public Tip createTip(String tipType, String message) {
        return generateTip(tipType, message);
    }


    public List<Tip> getAllTipsByDate(String date) {
        return repository.getRagTipByDate(date);
    }

    public Tip getTipByDateAndTipType(String date, String tipType) {
        return repository.getRagTipByDateAndTipType(date, tipType);
    }

    public Tip generateTip(String tipType, String message) {
        String response = chatClient.call(message);
        response = replaceMDTagsWithHtmlTags(response);
        Map<String, String> tipResponse = parseResponse(response);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDateTime.now().format(formatter);

        Tip tip = new Tip(null, tipType, formattedDate, tipResponse.get("question"), tipResponse.get("answer"));

        repository.save(tip);
        return tip;
    }

    public Map<String, String> parseResponse(String response) {
        Map<String, String> map = new HashMap<>();
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

    public String replaceMDTagsWithHtmlTags(String response) {

        return response.replaceAll("\\n", "<br>")
                .replaceFirst("\\*\\*", "<strong>")
                .replaceAll("\\*\\*", "</strong>")
                .replaceFirst("##", "<h2>")
                .replaceAll("##", "</h2>")
                .replaceAll("\\*", "-");
    }

    public String cleanQuestion(String question) {
        return question.replaceAll("<br>", "");
    }

    public String cleanAnswer(String answer) {
        return answer.replaceFirst("<br>", "");
    }


}
