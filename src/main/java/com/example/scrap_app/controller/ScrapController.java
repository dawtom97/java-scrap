package com.example.scrap_app.controller;
import com.example.scrap_app.service.ScrapService;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scrap")
public class ScrapController {

    @Autowired
    ScrapService scrapService;

    @GetMapping("/get-all")
    ResponseEntity<Map<String, Object>> getAll() {
        List<String> titles = scrapService.scrapAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Scrap successfully");
        response.put("code","200");
        response.put("data", titles);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-by-title")
    ResponseEntity<Map<String, Object>> getByTitle(@RequestBody Map<String,String> body) {

        String query = body.get("query");

        System.out.println(query);

        List<String> elements = scrapService.scrapByTitle(query);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Scrap successfully");
        response.put("code","200");
        response.put("data", elements);

        return ResponseEntity.ok(response);
    }
}
