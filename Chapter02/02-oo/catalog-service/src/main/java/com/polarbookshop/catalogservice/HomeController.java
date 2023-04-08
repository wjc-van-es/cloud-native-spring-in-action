package com.polarbookshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getGreeting(){
        return String.format("Greetings %s, welcome to the book catalog." +
                        "\n Running with Java %s, %s of %s at %s.\n" +
                        " OS/Arch/version: %s/%s/%s",
                System.getProperty("user.name"),
                System.getProperty("java.version"),
                System.getProperty("java.vendor.version"),
                System.getProperty("java.vendor"),
                System.getProperty("java.home"),
                System.getProperty("os.name"),
                System.getProperty("os.arch"),
                System.getProperty("os.version"));
    }
}
