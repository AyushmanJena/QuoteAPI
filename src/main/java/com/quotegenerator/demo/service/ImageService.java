package com.quotegenerator.demo.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ImageService{

    public void writeToImage(String quote, String author){
         System.out.println("started image processing");

        try{
            final BufferedImage image = ImageIO.read(new File("src/main/resources/static/test.png"));

            Graphics g = image.getGraphics();
            //g.setFont(g.getFont().deriveFont(40f));

            g.setFont(g.getFont().deriveFont(40f));
            int textWidth = g.getFontMetrics().stringWidth(quote);
            int authorWidth = g.getFontMetrics().stringWidth(author);
            int x = ((image.getWidth())/2) - (textWidth/2);
            int y = image.getHeight()/2;
            int authorx = ((image.getWidth())/2) - (authorWidth/2);

            g.drawString(quote, x, y);
            g.setFont(g.getFont().deriveFont(35f));
            g.drawString("- "+author, authorx, y+100 );
            g.dispose();
            ImageIO.write(image, "png", new File("src/main/java/com/quotegenerator/demo/storage/quote.png"));
            System.out.println("written to image");
        }
        catch(Exception e){
            System.out.println("didn't work");
        }
        System.out.println("completed image processing");
    }

    public ResponseEntity<InputStreamResource> downloadImage() throws IOException {
        File file = new File("src/main/java/com/quotegenerator/demo/storage/quote.png");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename = "+file.getName())
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(file.length())
                .body(resource);
    }

    public static void main(String[] args) throws Exception{
        ImageService img = new ImageService();
        img.writeToImage("this is some random quote", "vulcan");
    }
}
