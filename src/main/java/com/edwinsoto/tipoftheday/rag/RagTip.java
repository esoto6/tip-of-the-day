package com.edwinsoto.tipoftheday.rag;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tips")
public record RagTip(
        @Id String id,
        String tipType,
        String date,
        String question,
        String answer
        ) {
}
