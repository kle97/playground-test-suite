package io.playground.test.suite.test;

import io.playground.test.suite.common.BaseTest;
import io.playground.test.suite.common.Reporter;
import io.playground.test.suite.context.TestContext;
import io.playground.test.suite.page.EntityPage;
import io.playground.test.suite.page.Pages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
@RequiredArgsConstructor
public class SampleTest2 extends BaseTest {

    private final WebDriver driver;
    private final TestContext context;

    @BeforeClass
    public void beforeClass() {
        Reporter.appendReport("SampleTest2 - " + context.getName());
        driver.get("https://www.smogon.com/dex/xy/pokemon/" + context.getName().replace(".", "").replace(" ", "-").toLowerCase());
        Reporter.addScreenshotFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                                               context.getName());
    }

    @Test
    public void test1() {
        EntityPage entityPage = Pages.getEntityPage(driver);
        softly().as("Verify %s's name", context.getName())
                .assertThat(entityPage.getName())
                .isEqualToIgnoringCase(context.getName());
    }

    @Test
    public void test2() {
        EntityPage entityPage = Pages.getEntityPage(driver);
        softly().as("Verify %s's type(s)", context.getName())
                .assertThat(entityPage.getTypes())
                .containsExactlyInAnyOrderElementsOf(context.getTypes());
    }
}
