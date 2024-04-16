package com.edwinsoto.tipoftheday.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("javaTip", "Test data...");
        model.addAttribute("pythonTip", "Test data...");
        model.addAttribute("designTip", "Test data...");
        return "index";
    }


}
