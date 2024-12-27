package com.quotegenerator.demo.service;

import com.quotegenerator.demo.Model.Quote;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class QuoteService {
    public ArrayList<Quote> createQuotesList(){
        String[] content = {"Something first person said"
                , "Something second person said"
                , "Something third person said"
                , "Something fourth person said"
                , "Something fifth person said"
                , "Something sixth person said"
                , "Something seventh person said"
                , "Something eighth person said"};
        String[] author = {"first person", "second person", "third person", "fourth person", "fifth person", "sixth person", "seventh person", "eighth person"};
        ArrayList<Quote> quotes = new ArrayList<>();

        for(int i = 0; i<content.length; i++){
            String[] tags = {"valorant", "motivational", "life-changing"};
            Quote quote = new Quote(author[i], content[i], tags);
            quotes.add(quote);
        }
        return quotes;
    }

    public Quote getRandomQuote(ArrayList<Quote> quotes){
        int min = 0;
        int max = quotes.size()-1; // exclusive
        int randomNumber = (int) Math.round(min + Math.random() * (max - min));
        Quote randomQuote = quotes.get(randomNumber);
        return randomQuote;
    }
}
