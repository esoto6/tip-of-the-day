package com.edwinsoto.tipoftheday.controller;

import com.edwinsoto.tipoftheday.record.Tip;
import com.edwinsoto.tipoftheday.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/ollama")
public class TipController {


    private final TipService service;

    @Autowired
    public TipController(TipService service) {
        this.service = service;
    }


    @GetMapping("/java")
    public Tip createJavaTipOfTheDay(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Java posed as a question then answer") String message) {
        Tip tip = service.getTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "Java");
        return Objects.requireNonNullElseGet(tip, () -> service.createTip("Java", message));
    }

    @GetMapping("/python")
    public Tip createPythonTipOfTheDay(@RequestParam(value = "message", defaultValue = "Give me a developer tip on the topic of Python3 posed as a question then answer") String message) {
        Tip tip = service.getTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "Python");
        return Objects.requireNonNullElseGet(tip, () -> service.createTip("Python", message));
    }

    @GetMapping("/design")
    public Tip createDesignTipOfTheDay(@RequestParam(value = "message", defaultValue = "Give me a developer tip on software design posed as a question then answer") String message) {
        Tip tip = service.getTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "Design");
        return Objects.requireNonNullElseGet(tip, () -> service.createTip("Design", message));
    }

    @GetMapping("/{date}")
    public List<Tip> getAllTipsOfTheDay(@PathVariable String date) {
        return service.getAllTipsByDate(date);
    }

}
