package enlace.screenplay.stepdefinitions;

import enlace.screenplay.questions.TheDashboard;
import enlace.screenplay.questions.TheErrorMessage;
import enlace.screenplay.questions.TheLoginResult;
import enlace.screenplay.tasks.DoLogin;
import enlace.screenplay.tasks.NavigateToPortal;
import io.cucumber.java.es.*;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;


public class LoginSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

    @Dado("que el usuario navega al portal de Enlace")
    public void queElUsuarioNavegaAlPortalDeEnlace() {
        LOGGER.info("=== INICIANDO NAVEGACION AL PORTAL ===");

        Actor actor = OnStage.theActorCalled("Usuario");

        actor.attemptsTo(
            NavigateToPortal.loginPage()
        );

        LOGGER.info("Usuario navego exitosamente al portal");
    }

    @Cuando("el usuario ingresa con credenciales válidas")
    public void elUsuarioIngresaConCredencialesValidas(DataTable credenciales) {
        LOGGER.info("--- EJECUTANDO: Login con credenciales VALIDAS ---");

        Actor actor = OnStage.theActorInTheSpotlight();
        List<Map<String, String>> datos = credenciales.asMaps(String.class, String.class);
        String usuario = datos.get(0).get("usuario");
        String password = datos.get(0).get("password");

        LOGGER.info("Credenciales proporcionadas:");
        LOGGER.info("  - Usuario: {}", usuario);
        LOGGER.info("  - Password: *** (oculta)");

        actor.attemptsTo(
            DoLogin.withCredentials(usuario, password)
        );

        LOGGER.info("Login ejecutado");
    }

    @Cuando("el usuario intenta ingresar con credenciales inválidas")
    public void elUsuarioIntentaIngresarConCredencialesInvalidas(DataTable credenciales) {
        LOGGER.info("--- EJECUTANDO: Login con credenciales INVALIDAS ---");

        Actor actor = OnStage.theActorInTheSpotlight();
        List<Map<String, String>> datos = credenciales.asMaps(String.class, String.class);
        String usuario = datos.get(0).get("usuario");
        String password = datos.get(0).get("password");

        LOGGER.info("Credenciales proporcionadas:");
        LOGGER.info("  - Usuario: {}", usuario);
        LOGGER.info("  - Password: *** (oculta)");

        actor.attemptsTo(
            DoLogin.withCredentials(usuario, password)
        );

        LOGGER.info("Login ejecutado (se espera fallo)");
    }

    @Entonces("el usuario debería ver el dashboard del sistema")
    public void elUsuarioDeberiaVerElDashboardDelSistema() {
        LOGGER.info("--- VERIFICANDO: Dashboard visible ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.should(
            seeThat("el dashboard", TheDashboard.isVisible(), is(true))
        );

        LOGGER.info("Dashboard verificado correctamente");
    }

    @Y("el login debería ser exitoso")
    public void elLoginDeberiaSerExitoso() {
        LOGGER.info("--- VERIFICANDO: Login exitoso ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.should(
            seeThat("el resultado del login", TheLoginResult.isSuccessful(), is(true))
        );

        LOGGER.info("Login exitoso confirmado");
        LOGGER.info("=== ESCENARIO COMPLETADO EXITOSAMENTE ===");
    }

    @Entonces("el usuario debería ver un mensaje de error")
    public void elUsuarioDeberiaVerUnMensajeDeError() {
        LOGGER.info("--- VERIFICANDO: Mensaje de error visible ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.should(
            seeThat("el mensaje de error", TheErrorMessage.isDisplayed(), is(true))
        );

        LOGGER.info("Mensaje de error verificado correctamente");
    }

    @Y("el login debería fallar")
    public void elLoginDeberiaFallar() {
        LOGGER.info("--- VERIFICANDO: Login fallido ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.should(
            seeThat("el resultado del login", TheLoginResult.isFailed(), is(true))
        );

        LOGGER.info("Login fallido confirmado (comportamiento esperado)");
        LOGGER.info("=== ESCENARIO COMPLETADO EXITOSAMENTE ===");
    }
}
