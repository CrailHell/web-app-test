package com.pwc.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class JobSearchResultsPage {
    private final Page page;

    String jobList = "//table[@id='wdresults']/tbody/tr/td[1]/a";

    String pageListButton = "//button[@class='btn btn-secondary dropdown-toggle']";

    String pageListAllButton = "//div[@class='dropdown-menu']/a[.='All']";

    public JobSearchResultsPage(Page page) {
        this.page = page;
    }

    public JobDescriptionPage goToJobDescriptionPage(String jobTitle) {
        List<Locator> listOfJobs = page.locator(jobList).all();
        listOfJobs.stream().filter(job -> job.innerText().contains(jobTitle)).findFirst().get().click();
        return new JobDescriptionPage(page);
    }

    public JobSearchResultsPage selectAllJobsPerPage() {
        page.locator(pageListButton).click();
        page.locator(pageListAllButton).click();
        return this;
    }
}
