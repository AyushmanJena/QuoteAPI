package com.quotegenerator.demo.Controller;

import com.quotegenerator.demo.Model.Quote;
import com.quotegenerator.demo.service.ImageService;
import com.quotegenerator.demo.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class QuoteController {

    @Autowired
    public QuoteService quoteService;

    @Autowired
    public ImageService imageService;

    @GetMapping("/")
    public String index(){
        return "use \"/randomquote\" for a random quote      \"/allquotes\" for the list of quotes       \"/imageQuote\" to download a quote as image";
    }
    @GetMapping("/allquotes")
    public ArrayList<Quote> allQuotes(){
        ArrayList<Quote> quotes = quoteService.createQuotesList();
        return quotes;
    }

    @GetMapping("/randomquote") // with header
    public ResponseEntity<?> randomQuote(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("by","vulcan");

        ArrayList<Quote> quotes = quoteService.createQuotesList();
        Quote randomQuote = quoteService.getRandomQuote(quotes);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(randomQuote);
    }

    @GetMapping("/imageQuote")
    public ResponseEntity<?> imageQuote() throws Exception{
        ArrayList<Quote> quotes = quoteService.createQuotesList();
        Quote randomQuote = quoteService.getRandomQuote(quotes);
        String quote = randomQuote.getContent();
        String author = randomQuote.getAuthor();
        imageService.writeToImage(quote, author);

        return imageService.downloadImage();
//        return "Image written.";
    }
}