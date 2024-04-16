package com.edwinsoto.tipoftheday.tip;

import com.edwinsoto.tipoftheday.Controller.OllamaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TipService {

    @Autowired
    private TipRepository tipRepository;

    @Autowired
    private OllamaController ollamaController;

    public List<Tip> getTipOfTheDay(String date) {
        List<Tip> tips = tipRepository.findByDate(date);

        if (tips.isEmpty()){
            Tip tipOfTheDay = ollamaController.generateDesignTip("");

            tips.add(tipOfTheDay);
        }
        return tips;
    }

    public Tip addTip(Tip tip) {
        return tipRepository.save(tip);
    }
}
