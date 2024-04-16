package com.edwinsoto.tipoftheday.rag;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RagRepository extends MongoRepository<RagTip, String> {


    RagTip getRagTipByDateAndTipType(String date, String tipType);

    List<RagTip> findAllTipsByDate(String date);

    List<RagTip> getRagTipsByDate(String date);

//    List<OllamaTip> findOllamaTipByDate(String date);

//    RagTip generateTip();
}
