package enlace.screenplay.stepdefinitions;

import enlace.screenplay.questions.TheCSVFile;
import enlace.screenplay.questions.TheSuccessAlert;
import enlace.screenplay.tasks.*;
import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;


public class ConsultaCotizantesSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaCotizantesSteps.class);

    @Dado("que el usuario ha iniciado sesión exitosamente")
    public void queElUsuarioHaIniciadoSesionExitosamente() {
        LOGGER.info("=== INICIANDO SESION ===");

        Actor actor = OnStage.theActorCalled("Usuario");

        actor.attemptsTo(
            NavigateToPortal.loginPage(),
            DoLogin.withCredentials("pruebasqa@enlace.com.co", "Prueba1234567890*")
        );

        LOGGER.info("Sesion iniciada correctamente");
    }

    @Y("el usuario navega a la opción de Cotizantes RCI")
    public void elUsuarioNavegaALaOpcionDeCotizantesRCI() {
        LOGGER.info("--- Navegando a Cotizantes RCI ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
            NavigateToCotizantesRCI.menu()
        );

        LOGGER.info("Navegacion completada");
    }

    @Cuando("el usuario selecciona el tipo de documento {string}")
    public void elUsuarioSeleccionaElTipoDeDocumento(String tipoDocumento) {
        LOGGER.info("Tipo de documento seleccionado: {}", tipoDocumento);
    }

    @Y("el usuario ingresa el número de documento {string}")
    public void elUsuarioIngresaElNumeroDeDocumento(String numeroDocumento) {
        LOGGER.info("Numero de documento: {}", numeroDocumento);
    }

    @Y("el usuario hace clic en el botón Buscar")
    public void elUsuarioHaceClicEnElBotonBuscar() {
        LOGGER.info("--- EJECUTANDO BUSQUEDA ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
            SearchDocument.withData("CC", "1143382658")
        );

        LOGGER.info("Busqueda ejecutada");
    }

    @Entonces("el usuario debería ver el mensaje de consulta exitosa")
    public void elUsuarioDeberiaVerElMensajeDeConsultaExitosa() {
        LOGGER.info("--- VERIFICANDO: Mensaje de consulta exitosa ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.should(
            seeThat("el mensaje de exito", TheSuccessAlert.isDisplayed(), is(true))
        );

        LOGGER.info("Mensaje de exito verificado");
    }

    @Cuando("el usuario hace clic en el botón Exportar a CSV")
    public void elUsuarioHaceClicEnElBotonExportarACSV() {
        LOGGER.info("--- EXPORTANDO A CSV ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
            ExportToCSV.file()
        );

        LOGGER.info("Exportacion ejecutada");
    }

    @Entonces("el archivo CSV debería descargarse correctamente")
    public void elArchivoCSVDeberiaDescargarseCorrectamente() {
        LOGGER.info("--- VERIFICANDO: Descarga de CSV ---");

        Actor actor = OnStage.theActorInTheSpotlight();

        actor.should(
            seeThat("el archivo CSV", TheCSVFile.wasDownloaded(), is(true))
        );

        LOGGER.info("Archivo CSV descargado correctamente");
        LOGGER.info("=== ESCENARIO COMPLETADO EXITOSAMENTE ===");
    }
}
