package com.csc340.crud.greeting;

import com.csc340.crud.greeting.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sunny Ntini
 */
@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingRepository repo;

    private final Greeting mainGreeting = new Greeting(0, "Hello", "World");

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("mainGreeting", mainGreeting);
        return "greeting";
    }

    @GetMapping("/all")
    public String getAllGreetings(Model model) {
        model.addAttribute("mainGreeting", mainGreeting);
        model.addAttribute("greetingList", repo.getAllGreetings());
        return "greeting";
    }

    @GetMapping("/id={id}")
    public String getGreetingByID(@PathVariable("id") long id, Model model) {
        model.addAttribute("mainGreeting", repo.getGreetingById(id));
        model.addAttribute("greetingList", repo.getAllGreetings());
        return "greeting";
    }

    @RequestMapping("/addGreeting")
    @ResponseBody
    public String addItem(@RequestParam("message") String message,
            @RequestParam("recipient") String recipient) {
        if (repo.addGreeting(message, recipient) >= 1) {
            return "Greeting Added Successfully";
        } else {
            return "Something went wrong !";
        }
    }
}
