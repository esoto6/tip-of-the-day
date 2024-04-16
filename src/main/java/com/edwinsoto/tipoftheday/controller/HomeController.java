package com.edwinsoto.tipoftheday.controller;


import com.edwinsoto.tipoftheday.rag.RagRepository;
import com.edwinsoto.tipoftheday.rag.RagTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RagRepository ragRepository;

    @GetMapping("/")
    public String home(Model model) {
        List< RagTip> ragTips = ragRepository.getRagTipsByDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(ragTips.size());
        model.addAttribute("ragTips", ragTips);
        return "index";
    }

////        TODO:
//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("javaTip", ragRepository.getRagTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "java").toString());
//        model.addAttribute("pythonTip", ragRepository.getRagTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "python").toString());
//        model.addAttribute("designTip", ragRepository.getRagTipByDateAndTipType(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "design").toString());
//        return "home";
//    }

//    @GetMapping("/java")
//    public String javaTip(Model model){
//        model.addAttribute("javaTip", "Test data...");
//        return "java";
//    }
//
//    @GetMapping("/python")
//    public String pythonTip(Model model){
//        model.addAttribute("pythonTip", "Test data...");
//        return "python";
//    }
//
//    @GetMapping("/design")
//    public String designTip(Model model){
//        model.addAttribute("pythonTip", "Test data...");
//        return "design";
//    }

}
