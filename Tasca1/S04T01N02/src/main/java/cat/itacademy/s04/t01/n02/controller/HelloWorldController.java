package cat.itacademy.s04.t01.n02.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping("/HelloWorld")
    public String greeting(@RequestParam(defaultValue = "UNKNOWN") String name) {
        return "Hello, " + name + ". You're running a Gradle project.";
    }

    @RequestMapping(value = {"/HelloWorld2/", "/HelloWorld2/{name}" }, method = RequestMethod.GET)
    public String greeting2(@PathVariable(required = false) String name) {
        return "Hello, " + name + ". You're running a Gradle project.";
    }
}