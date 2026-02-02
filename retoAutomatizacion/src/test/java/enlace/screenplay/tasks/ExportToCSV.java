package enlace.screenplay.tasks;

import enlace.screenplay.ui.CotizantesRCIPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class ExportToCSV implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportToCSV.class);

    public static ExportToCSV file() {
        return new ExportToCSV();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LOGGER.info("=== INICIANDO EXPORTACION A CSV ===");

        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.BUTTON_EXPORT_CSV, isVisible()).forNoMoreThan(10).seconds(),
            Click.on(CotizantesRCIPage.BUTTON_EXPORT_CSV)
        );

        LOGGER.info("=== EXPORTACION EJECUTADA ===");
    }
}
