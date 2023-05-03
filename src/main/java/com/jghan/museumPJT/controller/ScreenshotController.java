package com.jghan.museumPJT.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenshotController {

    @GetMapping("/capture")
    public void captureScreen() throws IOException, AWTException {
    	String saveFilePath = "C:/";
        String saveFileName = "test";
        String saveFileExtension = "png";
        
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(rectangle);
            image.setRGB(0,0,100);
            
            File file = new File(saveFilePath+saveFileName+"."+saveFileExtension);
            ImageIO.write(image, saveFileExtension, file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}