package enlace.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToPortal implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigateToPortal.class);
    private static final String DEFAULT_URL = "https://pruebasportaldatos.enlace.com.co/login";

    public static NavigateToPortal loginPage() {
        return instrumented(NavigateToPortal.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String url = environmentVariables.getProperty("enlace.url", DEFAULT_URL);

        LOGGER.info("=== NAVEGANDO AL PORTAL ===");
        LOGGER.info("URL: {}", url);

        actor.attemptsTo(
            Open.url(url)
        );

        LOGGER.info("Página cargada exitosamente");
        LOGGER.info("=== NAVEGACIÓN COMPLETADA ===");
    }
}
