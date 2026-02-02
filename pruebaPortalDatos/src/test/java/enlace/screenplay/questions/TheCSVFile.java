package enlace.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

public class TheCSVFile implements Question<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheCSVFile.class);
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + File.separator + "downloadsCSV";
    private static final String DOCUMENTO_ESPERADO = "1143382658";

    public static TheCSVFile wasDownloaded() {
        return new TheCSVFile();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        LOGGER.info("=== VERIFICANDO DESCARGA Y CONTENIDO DEL CSV ===");
        LOGGER.info("Directorio de descargas: {}", DOWNLOAD_PATH);

        try {
            // Crear directorio si no existe
            Path downloadDir = Paths.get(DOWNLOAD_PATH);
            if (!Files.exists(downloadDir)) {
                Files.createDirectories(downloadDir);
                LOGGER.info("Directorio creado: {}", DOWNLOAD_PATH);
            }

            // Log detallado del estado del directorio
            LOGGER.info("=== DIAGNOSTICO DE DIRECTORIO ===");
            LOGGER.info("Ruta absoluta: {}", downloadDir.toAbsolutePath());
            LOGGER.info("Directorio existe: {}", Files.exists(downloadDir));
            LOGGER.info("Directorio es escribible: {}", Files.isWritable(downloadDir));

            // Listar todos los archivos presentes ANTES de esperar
            try (var files = Files.list(downloadDir)) {
                long fileCount = files.count();
                LOGGER.info("Archivos en directorio ANTES de descarga: {}", fileCount);
            } catch (Exception e) {
                LOGGER.warn("No se pudo listar archivos: {}", e.getMessage());
            }

            try (var files = Files.list(downloadDir)) {
                long fileCount = files.count();
                if (fileCount > 0) {
                    try (var filesForLog = Files.list(downloadDir)) {
                        filesForLog.forEach(path ->
                            LOGGER.info("  - Archivo existente: {} (tamaño: {} bytes)",
                                path.getFileName(),
                                path.toFile().length())
                        );
                    }
                }
            } catch (Exception e) {
                LOGGER.warn("No se pudo listar archivos detallados: {}", e.getMessage());
            }

            // Espera inteligente con polling (hasta 30 segundos)
            int maxWaitSeconds = 30;
            int pollIntervalSeconds = 2;
            int attempts = maxWaitSeconds / pollIntervalSeconds;
            Optional<File> latestFile = Optional.empty();

            LOGGER.info("Esperando descarga del archivo CSV (max {} segundos)...", maxWaitSeconds);

            for (int i = 0; i < attempts; i++) {
                Thread.sleep(pollIntervalSeconds * 1000);

                try (var files = Files.list(downloadDir)) {
                    latestFile = files
                        .filter(path -> {
                            String fileName = path.toString().toLowerCase();
                            // Verificar que sea CSV y NO sea archivo temporal
                            return fileName.endsWith(".csv")
                                && !fileName.endsWith(".crdownload")
                                && !fileName.endsWith(".tmp");
                        })
                        .map(Path::toFile)
                        .filter(file -> file.length() > 0) // Tamaño mayor a 0 bytes
                        .max(Comparator.comparingLong(File::lastModified));
                }

                if (latestFile.isPresent()) {
                    LOGGER.info("✓ Archivo CSV encontrado despues de {} segundos", (i + 1) * pollIntervalSeconds);
                    break;
                }

                LOGGER.debug("Intento {}/{}: Esperando archivo CSV...", i + 1, attempts);
            }

            if (!latestFile.isPresent()) {
                LOGGER.error("No se encontro archivo CSV en el directorio de descargas");
                return false;
            }

            File csvFile = latestFile.get();
            LOGGER.info("=== ARCHIVO CSV DESCARGADO ===");
            LOGGER.info("Nombre: {}", csvFile.getName());
            LOGGER.info("Ruta completa: {}", csvFile.getAbsolutePath());
            LOGGER.info("Tamaño: {} bytes ({} KB)", csvFile.length(), csvFile.length() / 1024);
            LOGGER.info("Ultima modificacion: {}", new java.util.Date(csvFile.lastModified()));
            LOGGER.info("Puede leerse: {}", csvFile.canRead());

            boolean documentoEncontrado = validateDocument(csvFile);

            if (documentoEncontrado) {
                LOGGER.info("=== DOCUMENTO {} ENCONTRADO EN EL CSV ===", DOCUMENTO_ESPERADO);

                boolean eliminado = csvFile.delete();
                if (eliminado) {
                    LOGGER.info("Archivo CSV eliminado exitosamente");
                } else {
                    LOGGER.warn("No se pudo eliminar el archivo CSV");
                }

                return true;
            } else {
                LOGGER.error("Documento {} NO encontrado en el CSV", DOCUMENTO_ESPERADO);
                return false;
            }

        } catch (Exception e) {
            LOGGER.error("Error verificando descarga de CSV: {}", e.getMessage(), e);
            return false;
        }
    }

    private boolean validateDocument(File csvFile) {
        LOGGER.info("--- Validando contenido del CSV ---");
        LOGGER.info("Buscando documento: {}", DOCUMENTO_ESPERADO);

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(DOCUMENTO_ESPERADO)) {
                    LOGGER.info("Documento encontrado en la linea {}: {}", lineNumber, line.substring(0, Math.min(100, line.length())));
                    return true;
                }
            }

            LOGGER.warn("Documento {} no encontrado en ninguna de las {} lineas del CSV", DOCUMENTO_ESPERADO, lineNumber);
            return false;

        } catch (Exception e) {
            LOGGER.error("Error leyendo el archivo CSV: {}", e.getMessage());
            return false;
        }
    }
}
