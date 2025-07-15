package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features/"},
        plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        glue = {"stepDefs","utils"},
        tags="@Test"
)

public class TestRunner {
}