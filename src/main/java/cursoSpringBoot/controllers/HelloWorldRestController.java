package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldRestController {

    @GetMapping({ "/hello", "/hello-world" })
    public String HelloWorld() {
        System.out.println("Solicitud ejecutada.");
        return "Hello World";
    }
}