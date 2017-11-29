package com.eitraz.talos.frontend;

import com.vaadin.annotations.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Push
public class TalosFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalosFrontendApplication.class, args);
    }
}
