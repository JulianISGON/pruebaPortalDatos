package enlace.screenplay.tasks;

import enlace.screenplay.ui.CotizantesRCIPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class NavigateToCotizantesRCI implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigateToCotizantesRCI.class);

    public static NavigateToCotizantesRCI menu() {
        return instrumented(NavigateToCotizantesRCI.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LOGGER.info("=== NAVEGANDO A COTIZANTES RCI ===");

        // Paso 1: Confirmar que el dashboard se cargó completamente
        LOGGER.info("Verificando que el dashboard esté cargado (Aviso legal)...");
        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.DASHBOARD_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
        LOGGER.info("✓ Dashboard cargado correctamente");

        // Paso 2: Esperar unos segundos más para asegurar que todo el sidebar esté renderizado
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Paso 3: Buscar y hacer clic en el menú Cotizantes RCI
        LOGGER.info("Buscando menú Cotizantes RCI (elemento visible, no móvil)...");
        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.MENU_COTIZANTES_RCI, isVisible()).forNoMoreThan(30).seconds()
        );

        LOGGER.info("Elemento encontrado, haciendo scroll...");
        actor.attemptsTo(
            Scroll.to(CotizantesRCIPage.MENU_COTIZANTES_RCI)
        );

        // Intentar hacer clic con JavaScript como respaldo
        try {
            LOGGER.info("Haciendo click en menú Cotizantes RCI con JavaScript...");
            WebElement elemento = CotizantesRCIPage.MENU_COTIZANTES_RCI.resolveFor(actor);
            JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
            js.executeScript("arguments[0].click();", elemento);
            LOGGER.info("✓ Click ejecutado con JavaScript");
        } catch (Exception e) {
            LOGGER.warn("Click con JavaScript falló, intentando click normal...");
            actor.attemptsTo(
                Click.on(CotizantesRCIPage.MENU_COTIZANTES_RCI)
            );
        }

        LOGGER.info("=== NAVEGACION COMPLETADA ===");
    }
}
