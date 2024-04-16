package com.edwinsoto.tipoftheday.tip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path="/tips")
public class TipController {


    private TipRepository tipsRepository;

    @Autowired
    public TipController(TipRepository tipsRepository) {
        this.tipsRepository = tipsRepository;
    }

    @GetMapping(path = "/today")
    public List<Tip> getTipOfTheDay(@PathVariable("today") String today) {
        if (today == null || today.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            today = LocalDate.now().format(formatter);
        }
        return tipsRepository.findByDate(today);
    }

    @PutMapping(path = "/save")
    public Tip saveTip(@RequestBody Tip tip) {
        return tipsRepository.save(tip);
    }
}
