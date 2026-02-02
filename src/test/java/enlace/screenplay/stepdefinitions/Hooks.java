package enlace.screenplay.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setTheStage(Scenario scenario) {
        LOGGER.info("  INICIANDO ESCENARIO: {}  ", scenario.getName());
        LOGGER.info("  Tags: {}  ", scenario.getSourceTagNames());

        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void afterScenario(Scenario scenario) {
        LOGGER.info("  ESCENARIO FINALIZADO: {}  ", scenario.getName());
        LOGGER.info("  Estado: {}  ", scenario.getStatus());

        OnStage.drawTheCurtain();
    }
}
