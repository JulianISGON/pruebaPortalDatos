package enlace.screenplay.tasks;

import enlace.screenplay.ui.CotizantesRCIPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class ExportToCSV implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportToCSV.class);

    public static ExportToCSV file() {
        return new ExportToCSV();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LOGGER.info("=== INICIANDO EXPORTACION A CSV ===");

        // Log del directorio ANTES del click
        Path downloadDir = Paths.get(System.getProperty("user.dir"), "downloadsCSV");
        try {
            if (Files.exists(downloadDir)) {
                try (var files = Files.list(downloadDir)) {
                    long fileCountBefore = files.count();
                    LOGGER.info("Archivos en directorio ANTES de exportar: {}", fileCountBefore);
                }
            } else {
                LOGGER.info("Archivos en directorio ANTES de exportar: 0");
            }
        } catch (Exception e) {
            LOGGER.warn("No se pudo contar archivos: {}", e.getMessage());
        }

        actor.attemptsTo(
            WaitUntil.the(CotizantesRCIPage.BUTTON_EXPORT_CSV, isVisible()).forNoMoreThan(10).seconds(),
            Click.on(CotizantesRCIPage.BUTTON_EXPORT_CSV)
        );

        LOGGER.info("✓ Click en boton Exportar ejecutado");

        // Espera breve para que el navegador inicie la descarga
        try {
            Thread.sleep(2000);
            LOGGER.info("✓ Espera post-click completada (2 segundos)");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warn("Espera interrumpida: {}", e.getMessage());
        }

        LOGGER.info("=== EXPORTACION EJECUTADA ===");
    }
}
