package com.example.scrap_app.views;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ViewController {


    @GetMapping("/")
    public String home(Model model) {



        model.addAttribute("title", "Strona główna");
        model.addAttribute("username","Jan Kowalski");

        List<Map<String, Object>> products = List.of(
                Map.of("name", "Laptop", "price", 4500.00, "manufacturer", "Dell","onSale",false),
                Map.of("name", "Smartfon", "price", 3200.00, "manufacturer", "Samsung","onSale",true),
                Map.of("name", "Klawiatura mechaniczna", "price", 350.00, "manufacturer", "Logitech", "onSale",true)
        );

        model.addAttribute("products", products);

        return "index";
    }

}
