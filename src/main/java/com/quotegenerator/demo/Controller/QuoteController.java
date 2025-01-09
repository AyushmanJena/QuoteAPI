package com.quotegenerator.demo.Controller;

import com.quotegenerator.demo.Model.Quote;
import com.quotegenerator.demo.service.ImageService;
import com.quotegenerator.demo.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class QuoteController {

    @Autowired
    public QuoteService quoteService;

    @Autowired
    public ImageService imageService;

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

    @GetMapping("/image-quote")
    public ResponseEntity<?> imageQuote() throws Exception{
        ArrayList<Quote> quotes = quoteService.createQuotesList();
        Quote randomQuote = quoteService.getRandomQuote(quotes);
        String quote = randomQuote.getContent();
        String author = randomQuote.getAuthor();
        imageService.writeToImage(quote, author);

        return imageService.downloadImage();
    }

    @GetMapping("/get-image")
    public ResponseEntity<?> getImage() {
        ArrayList<Quote> quotes = quoteService.createQuotesList();
        Quote randomQuote = quoteService.getRandomQuote(quotes);

        String quote = randomQuote.getContent();
        String author = randomQuote.getAuthor();

        System.out.println("Quote : "+ quote);
        System.out.println("Author : "+ author);
        imageService.writeToImage(quote, author);

        File file = new File("src/main/java/com/quotegenerator/demo/storage/quote.png");

        String encoded = null;

        try{
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encoded = new String(Base64.getEncoder().encodeToString(bytes));
            randomQuote.setEncodedImage(encoded);
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(encoded);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(randomQuote);
    }

}