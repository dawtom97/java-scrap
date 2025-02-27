package com.example.scrap_app.service;

import com.example.scrap_app.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Service
public class ScrapService {

    private WebDriver driver;

    private List<WebElement> getFromOnet() {

            driver = new SeleniumConfig().webDriver();

            driver.get("https://www.onet.pl");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement acceptButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".cmp-button_button.cmp-intro_acceptAll"))
            );
            acceptButton.click();

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3"))
            );

            List<WebElement> elements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("h3"))
            );

            return elements;
    }

    public List<String> scrapByTitle(String query) {

        List<String> results = new ArrayList<>();

        try {

            List<WebElement> titles = this.getFromOnet();
            for(WebElement title: titles) {
                if(title.getText().toLowerCase().contains(query.toLowerCase())) {
                    results.add(title.getText());
                }
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
            System.out.println("zamykam");
        }

        return results;
    }

    public List<String> scrapAll() {
        List<String> allTitles = new ArrayList<>();
        try {

            List<WebElement> titles = this.getFromOnet();

            for (WebElement title: titles) {
                allTitles.add(title.getText());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  finally {
            driver.quit();
            System.out.println("zamykam");
        }

        return allTitles;
    }

}
