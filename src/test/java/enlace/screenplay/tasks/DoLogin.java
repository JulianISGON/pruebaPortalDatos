package enlace.screenplay.tasks;

import enlace.screenplay.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class DoLogin implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoLogin.class);

    private final String username;
    private final String password;

    public DoLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static DoLogin withCredentials(String username, String password) {
        return instrumented(DoLogin.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LOGGER.info("=== INICIANDO LOGIN ===");
        LOGGER.info("Usuario: {}", username);
        LOGGER.debug("Contraseña: {}", maskPassword(password));

        actor.attemptsTo(
            WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible()).forNoMoreThan(15).seconds(),
            Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
            Enter.theValue(password).into(LoginPage.PASSWORD_FIELD)
        );

        // Intentar hacer clic en el botón o presionar Enter
        try {
            actor.attemptsTo(
                Click.on(LoginPage.LOGIN_BUTTON)
            );
            LOGGER.info("Botón de login presionado");
        } catch (Exception e) {
            LOGGER.warn("No se encontró botón de login, presionando ENTER en el campo de contraseña");
            actor.attemptsTo(
                Hit.the(Keys.ENTER).into(LoginPage.PASSWORD_FIELD)
            );
        }

        LOGGER.info("Credenciales ingresadas");
        LOGGER.info("=== LOGIN EJECUTADO ===");
    }

    private String maskPassword(String password) {
        if (password == null || password.length() < 3) {
            return "***";
        }
        return password.substring(0, 2) + "*".repeat(password.length() - 2);
    }
}
