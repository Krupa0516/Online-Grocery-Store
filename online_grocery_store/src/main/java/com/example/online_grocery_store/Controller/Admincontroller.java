package com.example.online_grocery_store.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Admincontroller {

    @RequestMapping("/adminhome")
    public String adminHome() {
        return "adminhome"; // The name of your adminhome.html page
    }

    @RequestMapping("/category1.html")
    public String category() {
        return "category1"; // The name of your Category.html page
    }

    @RequestMapping("/products1.html")
    public String Product() {
        return "products1"; // The name of your Category.html page
    }

}
