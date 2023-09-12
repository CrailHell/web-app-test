package com.pwc.pages;

import com.microsoft.playwright.Page;

public class JobSearchPage {
    private final Page page;

    String expCareerRolesButton = "//a[contains(text(),'Search PwC experienced careers roles')]";

    public JobSearchPage(Page page) {
        this.page = page;
    }

    public ExperiencedJobSearchPage goToExperiencedJobSearchPage() {
        page.locator(expCareerRolesButton).click();
        return new ExperiencedJobSearchPage(page);
    }
}
