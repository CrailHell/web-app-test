package com.pwc.pages;

import com.microsoft.playwright.Page;

public class JobDescriptionPage {
    private final Page page;

    String jobTitle = "//h1[@class='title-strip__heading']/span";
    String jobID = "//p[@class='wdid']/span";
    String jobLocation = "//p[@class='wdlocation']/span";
    String service = "//p[@class='wdservice']/span";
    String grade = "//p[@class='wdlevel']/span";

    public JobDescriptionPage(Page page) {
        this.page = page;
    }

    public String getJobTitle() {
        return page.locator(jobTitle).innerText();
    }

    public String getJobID() {
        return page.locator(jobID).innerText();
    }

    public String getJobLocation() {
        return page.locator(jobLocation).innerText();
    }

    public String getService() {
        return page.locator(service).innerText();
    }

    public String getGrade() {
        return page.locator(grade).innerText();
    }
}
