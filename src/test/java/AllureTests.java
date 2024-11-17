import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class AllureTests extends TestBase {


    @DisplayName("Проверка названия Issue с помощью Selenide и Listener")
    @Test
    void selenideIssueNameTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText(repoPath)).click();
        $("#issues-tab").click();
        $(withText(issueName)).shouldBe(Condition.exist);
    }

    @DisplayName("Проверка названия Issue с помощью лямбда шаги через step")
    @Test
    void lambdaIssueNameTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть главную страницу", () -> {
            open("");
        });
        step("Найти репозиторий " + repoPath, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(repoPath);
            $("#query-builder-test").submit();
        });
        step("Кликнуть по ссылке репозитория " + repoPath, () -> {
            $(linkText(repoPath)).click();
        });
        step("Открыть Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие Issue с названием " + issueName, () -> {
            $(withText(issueName)).should(Condition.exist);
        });
    }

    @DisplayName("Проверка названия Issue с помощью аннотаций")
    @Test
    void annotationIssueNameTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepsTests steps = new StepsTests();

        steps.openMainPage();
        steps.searchForRepository(repoPath);
        steps.clickOnRepositoryLink(repoPath);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(issueName);
    }

}
