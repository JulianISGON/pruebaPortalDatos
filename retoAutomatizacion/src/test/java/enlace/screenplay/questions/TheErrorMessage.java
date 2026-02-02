package enlace.screenplay.questions;

import enlace.screenplay.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheErrorMessage implements Question<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheErrorMessage.class);

    public static TheErrorMessage isDisplayed() {
        return new TheErrorMessage();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        LOGGER.info("Verificando visibilidad de mensaje de error (Usuario o contraseña incorrectos)...");

        try {
            Thread.sleep(2000); // Espera para que aparezca el mensaje
            boolean visible = Visibility.of(LoginPage.ERROR_MESSAGE).answeredBy(actor);
            LOGGER.info("Mensaje de error visible: {}", visible);
            return visible;
        } catch (Exception e) {
            LOGGER.error("Error verificando mensaje: {}", e.getMessage());
            return false;
        }
    }
}
