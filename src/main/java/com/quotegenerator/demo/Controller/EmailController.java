package com.quotegenerator.demo.Controller;

import com.quotegenerator.demo.Model.Quote;
import com.quotegenerator.demo.service.EmailService;
import com.quotegenerator.demo.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/mail")
public class EmailController {

    public class Email{
        String email;

        public Email() {}

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    @Autowired
    private EmailService emailService;

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/send-mail")
    public String showEmailFormPage(Model theModel){
        theModel.addAttribute("email", new Email());
        return "email-form";
    }

    @PostMapping("/processEmailForm")
    public String processForm(@ModelAttribute("email") String email, Model model){
        System.out.println(email);

        ArrayList<Quote> quotes = quoteService.createQuotesList();
        Quote randomQuote = quoteService.getRandomQuote(quotes);

        String body = randomQuote.getAuthor() + " once said \"" + randomQuote.getContent()+"\"";
        boolean sent = false;

        try{
            emailService.sendEmail(email, "Quote Generator" , body);
            sent = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("sent", sent);
        return "email-sent";

    }

//    @GetMapping("/confirm-email-sent")
//    public String confirmEmailSent(@RequestParam(name = "sent") boolean sent){
//        if(sent){
//            return "email-sent/"+"true";
//        }
//        return "email-sent/"+"false";
//    }
//    @Controller
//    public class MyController {
//
//        @GetMapping("/example")
//        public String example(@RequestParam(name = "myVariable") String myVariable, Model model) {
//            model.addAttribute("myVariable", myVariable);
//            return "example"; // Name of your Thymeleaf template
//        }
//    }
}
