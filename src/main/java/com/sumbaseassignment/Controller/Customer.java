package com.sumbaseassignment.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Customer {

    @GetMapping("/user")
    public String geuserDetails(){
        System.out.print("Getting User");
        return "users";
    }


//    @PostMapping("/create")
}
