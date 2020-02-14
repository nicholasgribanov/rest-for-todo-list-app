package name.nicholasgribanov.restforweb.controllers;

import name.nicholasgribanov.restforweb.model.BasicAuthBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class BasicAuthController {

    @GetMapping("/basicauth")
    public BasicAuthBean getHelloWorld() {
        return new BasicAuthBean("Hello world!!!!");
    }

    @GetMapping("/hello/{name}")
    public BasicAuthBean getHelloWorldParameter(@PathVariable String name) {
        return new BasicAuthBean("Hello world, " + name);
    }
}


