package com.edwinsoto.tipoftheday.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tips")
public record Tip(
        @Id String id,
        @NotBlank String tipType,
        @Pattern(regexp = "\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])") String date,
        String question,
        String answer
        ) {
}
