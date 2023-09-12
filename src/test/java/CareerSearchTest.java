import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.pwc.pages.HomePage;
import com.pwc.pages.JobDescriptionPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CareerSearchTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    HomePage homePage;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        homePage = new HomePage(context.newPage())
                .goToHomePage()
                .acceptAllCookies();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @ParameterizedTest
    @CsvSource({"Czech Republic,Automation Engineer", "Poland,Architekt IT", "Netherlands,Applicatiebeheerder"})
    void shouldFindOpenPosition(String country, String expectedJobTitle) {
        String actualJobTitle = homePage
                .goToJobSearchPage()
                .goToExperiencedJobSearchPage()
                .selectCountryByValue(country)
                .clickOnSearchButton()
                .selectAllJobsPerPage()
                .goToJobDescriptionPage(expectedJobTitle)
                .getJobTitle();

        Assertions.assertEquals(expectedJobTitle, actualJobTitle);
    }

    @ParameterizedTest
    @CsvSource({
            "Czech Republic,Automation Engineer,455014WD,Prague,Assurance,Senior Associate",
            "Poland,Architekt IT,235415WD,Warszawa,Advisory,Senior Associate",
            "Netherlands,Applicatiebeheerder,359815WD,Amsterdam,Internal Firm Services,Associate"})
    void shouldFindJobByTitle(String country, String expectedJobTitle, String expectedJobID, String expectedJobLocation,
                              String expectedService, String expectedGrade) {

        JobDescriptionPage jobDescriptionPage = homePage
                .goToJobSearchPage()
                .goToExperiencedJobSearchPage()
                .selectCountryByValue(country)
                .enterJobTitle(expectedJobTitle)
                .clickOnSearchButton()
                .goToJobDescriptionPage(expectedJobTitle);

        assertAll(
                "Group assertion of Job Description page data",
                () -> assertEquals(expectedJobTitle, jobDescriptionPage.getJobTitle()),
                () -> assertEquals(expectedJobID, jobDescriptionPage.getJobID()),
                () -> assertEquals(expectedJobLocation, jobDescriptionPage.getJobLocation()),
                () -> assertEquals(expectedService, jobDescriptionPage.getService()),
                () -> assertEquals(expectedGrade, jobDescriptionPage.getGrade())
        );
    }
}
