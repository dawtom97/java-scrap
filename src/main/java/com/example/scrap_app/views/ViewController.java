package com.example.scrap_app.views;


import com.example.scrap_app.model.ScrapModel;
import com.example.scrap_app.service.ScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class ViewController {

    @Autowired
    private RestTemplate restTemplate;




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

    @GetMapping("/news")
    public String news(Model model) {

        String apiUrl = "http://localhost:8080/api/scrap/get-all";

        ResponseEntity<Map<String,Object>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        List<Map<String,Object>> scraps = (List<Map<String,Object>>) response.getBody().get("data");

        model.addAttribute("scraps",scraps);


        return "news";
    }

    @GetMapping("/add-news")
    public String addNewsForm() {
        return "scrap-form";
    }


    @GetMapping("/delete/{id}")
    public String deleteNews(@PathVariable String id) {
        String apiUrl = "http://localhost:8080/api/scrap/delete/" + id;
        ResponseEntity<Map<String,Object>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );
        return "redirect:/news";
    }

}
