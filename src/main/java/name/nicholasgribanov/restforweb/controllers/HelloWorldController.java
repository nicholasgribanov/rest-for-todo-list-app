package name.nicholasgribanov.restforweb.controllers;

import name.nicholasgribanov.restforweb.model.HelloWorld;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public HelloWorld getHelloWorld() {
        return new HelloWorld("Hello world!!!!");
    }
}
