package enlace.screenplay.tasks;

import enlace.screenplay.ui.CotizantesRCIPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SearchDocument implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchDocument.class);

    private final String tipoDocumento;
    private final String numeroDocumento;

    public SearchDocument(String tipoDocumento, String numeroDocumento) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public static SearchDocument withData(String tipoDocumento, String numeroDocumento) {
        return new SearchDocument(tipoDocumento, numeroDocumento);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LOGGER.info("=== INICIANDO BUSQUEDA DE DOCUMENTO ===");
        LOGGER.info("Tipo: {}", tipoDocumento);
        LOGGER.info("Numero: {}", numeroDocumento);

        // Primero validamos que estamos en la página de Cotizantes RCI
        LOGGER.info("Validando que estamos en la página de Cotizantes RCI...");
        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.PAGE_TITLE_COTIZANTES_RCI, isVisible()).forNoMoreThan(10).seconds()
        );
        LOGGER.info("✓ Página de Cotizantes RCI cargada correctamente");

        // Seleccionamos el tipo de documento
        LOGGER.info("Seleccionando tipo de documento: {}", tipoDocumento);
        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.SELECT_DOCUMENT_TYPE, isVisible()).forNoMoreThan(10).seconds(),
            SelectFromOptions.byValue(tipoDocumento).from(CotizantesRCIPage.SELECT_DOCUMENT_TYPE)
        );
        LOGGER.info("✓ Tipo de documento seleccionado");

        // Ingresamos el número de documento
        LOGGER.info("Ingresando número de documento: {}", numeroDocumento);
        actor.attemptsTo(
            Enter.theValue(numeroDocumento).into(CotizantesRCIPage.INPUT_DOCUMENT_NUMBER)
        );
        LOGGER.info("✓ Número de documento ingresado");

        // Hacemos clic en el botón Buscar
        LOGGER.info("Haciendo clic en el botón Buscar...");
        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.BUTTON_SEARCH, isVisible()).forNoMoreThan(5).seconds(),
            Click.on(CotizantesRCIPage.BUTTON_SEARCH)
        );

        LOGGER.info("=== BUSQUEDA EJECUTADA ===");
    }
}
