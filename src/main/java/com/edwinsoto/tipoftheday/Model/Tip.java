package com.edwinsoto.tipoftheday.Model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tips")
public class Tip {
    @Id
    private String id;
    private String question;
    private String answer;
    private String dateTime;
    private String endpoint;

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
