package enlace.screenplay.questions;

import enlace.screenplay.ui.CotizantesRCIPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TheSuccessAlert implements Question<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheSuccessAlert.class);

    public static TheSuccessAlert isDisplayed() {
        return new TheSuccessAlert();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        LOGGER.info("Verificando alerta de consulta exitosa...");

        try {
            Thread.sleep(2000); // Espera para que aparezca el mensaje
            boolean visible = Visibility.of(CotizantesRCIPage.SUCCESS_ALERT).answeredBy(actor);
            LOGGER.info("Alerta de exito visible: {}", visible);
            return visible;
        } catch (Exception e) {
            LOGGER.error("Error verificando alerta: {}", e.getMessage());
            return false;
        }
    }
}
