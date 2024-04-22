package com.edwinsoto.tipoftheday.service;

import com.edwinsoto.tipoftheday.record.Tip;
import com.edwinsoto.tipoftheday.repository.TipRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TipServiceTest {

    @MockBean
    private OllamaChatClient chatClient;
    @MockBean
    private TipRepository repository;

    @Autowired
    private TipService tipService;
    @Autowired
    private OllamaChatClient ollamaChatClient;

    @Disabled
    @Test
    void testGenerateTip() {
        String mockResponse = """
                **Question:** What is the difference between `final` and `const` keywords in Java?
                                
                **Answer:**
                                
                The `final` keyword is used to declare a variable that cannot be reassigned. It is used for variables whose value should not change once they are initialized. For example:
                                
                ```java
                final int x = 10;
                x = 20; // Compilation error: cannot assign a value to a final variable
                ```
                                
                The `const` keyword is used to declare a variable that is initialized at compile time. It is similar to `final`, but it is used for constants that are known at compile time. For example:
                                
                ```java
                const int y = 20;
                y = 30; // Compilation error: cannot assign a value to a const variable
                ```
                                
                The main difference between `final` and `const` is that `final` variables can be reassigned, while `const` variables cannot. This makes `final` variables more flexible, as they can be initialized with different values at runtime. However, `const` variables are more efficient, as their value is known at compile time.
                """;

        when(ollamaChatClient.call("Give me")).thenReturn(mockResponse);

        Tip generatedTip = tipService.generateTip("Java", "Give me a developer tip on the topic of Java posed as a question then answer");

        verify(chatClient).call("Give me a developer tip on the topic of Java posed as a question then answer");

        verify(repository).save(generatedTip);

        assertEquals("Java", generatedTip.tipType());
        assertEquals("What is the difference between 'final' and 'const' keywords in Java?", generatedTip.question());
        assertEquals("The `final` keyword is used to declare a variable that cannot be reassigned. It is used for variables whose value should not change once they are initialized. For example:\n" +
                "                                \n" +
                "                ```java\n" +
                "                final int x = 10;\n" +
                "                x = 20; // Compilation error: cannot assign a value to a final variable\n" +
                "                ```\n" +
                "                                \n" +
                "                The `const` keyword is used to declare a variable that is initialized at compile time. It is similar to `final`, but it is used for constants that are known at compile time. For example:\n" +
                "                                \n" +
                "                ```java\n" +
                "                const int y = 20;\n" +
                "                y = 30; // Compilation error: cannot assign a value to a const variable\n" +
                "                ```\n" +
                "                                \n" +
                "                The main difference between `final` and `const` is that `final` variables can be reassigned, while `const` varia", generatedTip.answer());
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), generatedTip.date());
    }

    @Test
    @Disabled("Not sure how to test yet")
    void testCreateTip() {
//        Tip mockTip = new Tip("abc", "Java", "2024-04-21", "Quesiton..", "Answer...");
//
//        Mockito.when(service.generateTip("Java", "Question.. Blah")).thenReturn(mockTip);
//
//        verify(service).createTip("Java", "Question... Blah");

        when(chatClient.call("This is a test message")).thenReturn("""
                **Question:** What is the difference between `final` and `const` keywords in Java?
                                
                **Answer:**
                                
                The `final` keyword is used to declare a variable that cannot be reassigned. It is used for variables whose value should not change once they are initialized. For example:
                                
                ```java
                final int x = 10;
                x = 20; // Compilation error: cannot assign a value to a final variable
                ```
                                
                The `const` keyword is used to declare a variable that is initialized at compile time. It is similar to `final`, but it is used for constants that are known at compile time. For example:
                                
                ```java
                const int y = 20;
                y = 30; // Compilation error: cannot assign a value to a const variable
                ```
                                
                The main difference between `final` and `const` is that `final` variables can be reassigned, while `const` variables cannot. This makes `final` variables more flexible, as they can be initialized with different values at runtime. However, `const` variables are more efficient, as their value is known at compile time.
                """);

    }

    @Test
    void getAllTipsByDate() {
    }

    @Test
    void getTipByDateAndTipType() {
    }
}