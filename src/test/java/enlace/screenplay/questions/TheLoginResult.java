package enlace.screenplay.questions;

import enlace.screenplay.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Question para verificar el resultado del login
 */
public class TheLoginResult implements Question<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheLoginResult.class);

    private final LoginResultType resultType;

    public enum LoginResultType {
        SUCCESSFUL,
        FAILED
    }

    private TheLoginResult(LoginResultType resultType) {
        this.resultType = resultType;
    }

    public static TheLoginResult isSuccessful() {
        return new TheLoginResult(LoginResultType.SUCCESSFUL);
    }

    public static TheLoginResult isFailed() {
        return new TheLoginResult(LoginResultType.FAILED);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        LOGGER.info("=== VERIFICANDO RESULTADO DEL LOGIN ===");
        LOGGER.info("Tipo de verificación: {}", resultType);

        boolean result;

        try {
            if (resultType == LoginResultType.SUCCESSFUL) {
                Thread.sleep(2000); // Espera para que la página cargue completamente
                result = Visibility.of(LoginPage.DASHBOARD_TITLE).answeredBy(actor);
                LOGGER.info("Dashboard (Aviso legal) visible: {}", result);
            } else {
                Thread.sleep(1000);
                boolean dashboardVisible = Visibility.of(LoginPage.DASHBOARD_TITLE).answeredBy(actor);
                result = !dashboardVisible; // Login falló si NO hay dashboard
                LOGGER.info("Dashboard NO visible (login fallido confirmado): {}", result);
            }

        } catch (Exception e) {
            LOGGER.error("Error verificando resultado del login: {}", e.getMessage());
            result = false;
        }

        LOGGER.info("Resultado final de verificación: {}", result);
        LOGGER.info("=== VERIFICACIÓN COMPLETADA ===");

        return result;
    }
}
