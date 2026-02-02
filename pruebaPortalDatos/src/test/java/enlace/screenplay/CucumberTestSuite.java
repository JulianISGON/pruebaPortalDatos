package enlace.screenplay;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner principal para ejecutar las pruebas con Cucumber y Serenity
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "enlace.screenplay.stepdefinitions",
    plugin = {"pretty", "json:target/cucumber.json"},
    tags = "@test"
)
public class CucumberTestSuite {
}
