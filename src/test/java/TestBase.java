import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static final String repoPath = "eroshenkoam/allure-example";
    static final String issueName = "Тестируем тест";

    @BeforeAll
    static void preconditionsBeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }


}
