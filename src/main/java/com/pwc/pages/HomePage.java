package com.pwc.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;

    String acceptAllCookiesButton = "//button[@id='onetrust-accept-btn-handler']";
    String careerButton = "//a[@aria-controls='careers-subnav-5']";
    String careersDropDownMenu = "//div[@id='careers-subnav-5']//nav/a";

    public HomePage(Page page) {
        this.page = page;
    }

    public HomePage goToHomePage() {
        page.navigate("https://www.pwc.com/gx/en");
        return this;
    }

    public HomePage acceptAllCookies() {
        page.locator(acceptAllCookiesButton).click();
        return this;
    }

    public JobSearchPage goToJobSearchPage() {
        page.locator(careerButton).click();
        page.locator(careersDropDownMenu).all().get(1).click();
        return new JobSearchPage(page);
    }
}
