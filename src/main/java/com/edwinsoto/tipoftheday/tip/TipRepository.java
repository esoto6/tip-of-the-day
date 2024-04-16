package com.edwinsoto.tipoftheday.tip;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipRepository extends MongoRepository<Tip, String> {

//    Tip findJavaTipOfTheDay(String date);
//
//    Tip findPythonTipOfTheDay(String date);
//
//    Tip findDesignTipOfTheDay(String date);

    List<Tip> findByDate(String date);

//    Tip addTip(Tip tip);
}
