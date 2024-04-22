package com.edwinsoto.tipoftheday.repository;

import com.edwinsoto.tipoftheday.record.Tip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipRepository extends MongoRepository<Tip, String> {


    Tip getRagTipByDateAndTipType(String date, String tipType);

    List<Tip> getRagTipByDate(String date);
}
