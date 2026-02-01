package enlace.screenplay.tasks;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToPortal implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigateToPortal.class);
    private static final String DEFAULT_URL = "https://pruebasportaldatos.enlace.com.co/login";

    private final LoginPageObject loginPage = new LoginPageObject();

    public static NavigateToPortal loginPage() {
        return instrumented(NavigateToPortal.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = System.getProperty("serenity.enlace.url", DEFAULT_URL);

        LOGGER.info("=== NAVEGANDO AL PORTAL ===");
        LOGGER.info("URL: {}", url);

        actor.attemptsTo(
            Open.browserOn(loginPage)
        );

        LOGGER.info("Página cargada exitosamente");
        LOGGER.info("=== NAVEGACIÓN COMPLETADA ===");
    }

    public static class LoginPageObject extends PageObject {
        // Serenity usa el valor de serenity.properties por defecto
    }
}
