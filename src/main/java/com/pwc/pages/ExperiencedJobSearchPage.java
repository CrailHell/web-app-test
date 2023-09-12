package com.pwc.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class ExperiencedJobSearchPage {
    private final Page page;

    String selectCountryButton = "//select[@id='wdcountry']";
    String jobTitleInput = "//input[@id='input-job-search']";
    String searchButton = "//*[@id='wd-jobsearch']/p/input[@type='submit']";

    public ExperiencedJobSearchPage(Page page) {
        this.page = page;
    }

    public ExperiencedJobSearchPage selectCountryByValue(String value) {
        ElementHandle handle = page.querySelector(selectCountryButton);
        handle.selectOption(value);
        return this;
    }

    public ExperiencedJobSearchPage enterJobTitle(String jobTitle) {
        page.locator(jobTitleInput).fill(jobTitle);
        return this;
    }

    public JobSearchResultsPage clickOnSearchButton() {
        page.locator(searchButton).click();
        return new JobSearchResultsPage(page);
    }
}
