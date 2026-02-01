package enlace.screenplay.questions;

import enlace.screenplay.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheDashboard implements Question<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheDashboard.class);

    public static TheDashboard isVisible() {
        return new TheDashboard();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        LOGGER.info("Verificando visibilidad del dashboard (título: Aviso legal)...");

        try {
            Thread.sleep(3000); // Espera para carga completa
            boolean visible = Visibility.of(LoginPage.DASHBOARD_TITLE).answeredBy(actor);
            LOGGER.info("Dashboard (Aviso legal) visible: {}", visible);
            return visible;
        } catch (Exception e) {
            LOGGER.error("Error verificando dashboard: {}", e.getMessage());
            return false;
        }
    }
}
